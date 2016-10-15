print('Palindrome Test')
while True:
    uinput = input('Please enter a string (exit to end): ').lower()
    if uinput == 'exit':
        print('Goodbye')
        break
    elif uinput == uinput[::-1]:
        print(uinput, 'is a palindrome')
    else:
        print(uinput, ' is not a palindrome')