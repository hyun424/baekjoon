n=int(input())
stocks=list(map(int,input().split()))
def bnp(n):
    bnp_cash=n
    bnp_stock=0
    for stock in stocks:
        if (bnp_cash>=stock):
            bnp_stock+=bnp_cash//stock
            bnp_cash-=stock*(bnp_cash//stock)
    res=bnp_stock*stocks[-1]+bnp_cash
    return res
def timing(n):
    timing_cash=n
    timing_stock=0
    plus,minus=0,0
    temp=stocks[0]
    for index,stock in enumerate (stocks):
        #세번 연속 하락하면 풀매수
        if temp>stock:
            minus+=1
            plus=0
            temp=stock
            if minus>=3:
                timing_stock+=timing_cash//stock
                timing_cash-=(timing_cash//stock)*stock
                #print(stock,timing_stock,timing_cash)
        elif temp<stock:
            plus+=1
            minus=0
            temp=stock
            if plus>=3:
                timing_cash+=timing_stock*stock
                timing_stock=0
    res=timing_cash+timing_stock*stocks[-1]
    return res
#print(bnp(n),timing(n))
if bnp(n)>timing(n):
    print("BNP")
elif(bnp(n)<timing(n)):
    print("TIMING")
else:
    print("SAMESAME")