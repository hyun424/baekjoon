/**
 * Cloudflare Worker: Discord Interaction → GitHub Actions repository_dispatch
 *
 * Discord 버튼 클릭 수신 → custom_id 파싱 → GitHub API 호출 → Discord 즉시 응답
 *
 * Environment Variables (Cloudflare Worker Secrets):
 *   DISCORD_PUBLIC_KEY  - Discord Application Public Key
 *   GH_PAT              - GitHub Personal Access Token (repo scope)
 */

const QUALITY_LABELS = { "1": "😰 모름", "3": "🤔 어렴풋이", "5": "😊 기억남" };
const INTERVAL_HINT = { "1": "1일 후", "3": "현재 단계 유지", "5": "다음 단계로!" };

export default {
  async fetch(request, env) {
    if (request.method !== "POST") {
      return new Response("Method not allowed", { status: 405 });
    }

    // Discord signature verification
    const signature = request.headers.get("X-Signature-Ed25519");
    const timestamp = request.headers.get("X-Signature-Timestamp");
    const body = await request.text();

    const isValid = await verifyDiscordSignature(body, signature, timestamp, env.DISCORD_PUBLIC_KEY);
    if (!isValid) {
      return new Response("Invalid signature", { status: 401 });
    }

    const interaction = JSON.parse(body);

    // PING (Discord verification)
    if (interaction.type === 1) {
      return jsonResponse({ type: 1 });
    }

    // MESSAGE_COMPONENT (button click)
    if (interaction.type === 3) {
      const customId = interaction.data.custom_id;
      // Format: review_{problemId}_{quality}
      const match = customId.match(/^review_(.+)_(\d)$/);
      if (!match) {
        return jsonResponse({
          type: 4,
          data: { content: "❌ 잘못된 버튼입니다.", flags: 64 },
        });
      }

      const [, problemId, quality] = match;
      const label = QUALITY_LABELS[quality] || quality;
      const hint = INTERVAL_HINT[quality] || "";

      // Trigger GitHub Actions
      try {
        await triggerGitHubDispatch(env, problemId, quality);
      } catch (err) {
        return jsonResponse({
          type: 4,
          data: { content: `❌ GitHub 트리거 실패: ${err.message}`, flags: 64 },
        });
      }

      return jsonResponse({
        type: 4,
        data: {
          content: `✅ **${label}** 반영! (${hint})\n문제: \`${problemId}\``,
          flags: 64, // EPHEMERAL - 본인만 볼 수 있음
        },
      });
    }

    return jsonResponse({ type: 1 });
  },
};

async function triggerGitHubDispatch(env, problemId, quality) {
  const url = "https://api.github.com/repos/hyun424/baekjoon/dispatches";
  const resp = await fetch(url, {
    method: "POST",
    headers: {
      Authorization: `Bearer ${env.GH_PAT}`,
      Accept: "application/vnd.github.v3+json",
      "User-Agent": "review-bot-worker",
      "Content-Type": "application/json",
    },
    body: JSON.stringify({
      event_type: "review_feedback",
      client_payload: { problem_id: problemId, quality: parseInt(quality) },
    }),
  });
  if (!resp.ok) {
    const text = await resp.text();
    throw new Error(`${resp.status}: ${text}`);
  }
}

async function verifyDiscordSignature(body, signature, timestamp, publicKey) {
  try {
    const encoder = new TextEncoder();
    const key = await crypto.subtle.importKey(
      "raw",
      hexToUint8Array(publicKey),
      { name: "Ed25519", namedCurve: "Ed25519" },
      false,
      ["verify"]
    );
    const message = encoder.encode(timestamp + body);
    return await crypto.subtle.verify("Ed25519", key, hexToUint8Array(signature), message);
  } catch {
    return false;
  }
}

function hexToUint8Array(hex) {
  const bytes = new Uint8Array(hex.length / 2);
  for (let i = 0; i < hex.length; i += 2) {
    bytes[i / 2] = parseInt(hex.substring(i, i + 2), 16);
  }
  return bytes;
}

function jsonResponse(data) {
  return new Response(JSON.stringify(data), {
    headers: { "Content-Type": "application/json" },
  });
}
