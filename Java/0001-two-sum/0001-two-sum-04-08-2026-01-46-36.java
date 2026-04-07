class Solution {
    public int[] twoSum(int[] nums, int target) {

        HashMap<Integer, Integer> temp = new HashMap<>();

        for(int i = 0; i < nums.length; i++){
            int curr = nums[i];
            int exp = target - curr;

            if(temp.containsKey(exp)){
                return new int[]{i, temp.get(exp)};
            }

            temp.put(curr, i);
        }

        return null;
    }
}