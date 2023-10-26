def compute(n):
    if n < 10:
        out = n ** 2
    elif n < 20:
        out = 1
        for i in range(1, n-9):  # Bug-1 > The loop here needs to run from 1 and          
            out *= i             # end at n-9 instead of n-10
    else:
        lim = n - 20
        out = lim * lim
        out = out - lim
        out = out / 2 + lim      # Bug-2 > The lim variable needs to be added to 
    print(out)                   # out/2 for correct output


n = int(input("Enter an integer: "))
compute(n)