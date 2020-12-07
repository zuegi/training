package ch.wesr.training.kata;

// https://www.codewars.com/kata/526c7b931666d07889000a3c/train/java
//https://stackoverflow.com/questions/37331227/java-solving-mathematical-operations-from-strings
public class BefungeInterpreter {

    public String interpret(String code) {



        return "";
    }

    /*
        0-9 Push this number onto the stack.
        + Addition: Pop a and b, then push a+b.
        - Subtraction: Pop a and b, then push b-a.
        * Multiplication: Pop a and b, then push a*b.
        / Integer division: Pop a and b, then push b/a, rounded down. If a is zero, push zero.
        % Modulo: Pop a and b, then push the b%a. If a is zero, push zero.
        ! Logical NOT: Pop a value. If the value is zero, push 1; otherwise, push zero.
        ` (backtick) Greater than: Pop a and b, then push 1 if b>a, otherwise push zero.
        > Start moving right.
        < Start moving left.
        ^ Start moving up.
        v Start moving down.
        ? Start moving in a random cardinal direction.
        _ Pop a value; move right if value = 0, left otherwise.
        | Pop a value; move down if value = 0, up otherwise.
        " Start string mode: push each character's ASCII value all the way up to the next ".
        : Duplicate value on top of the stack. If there is nothing on top of the stack, push a 0.
        \ Swap two values on top of the stack. If there is only one value, pretend there is an extra 0 on bottom of the stack.
        $ Pop value from the stack and discard it.
        . Pop value and output as an integer.
        , Pop value and output the ASCII character represented by the integer code that is stored in the value.
        # Trampoline: Skip next cell.
        p A "put" call (a way to store a value for later use). Pop y, x and v, then change the character at the position (x,y) in the program to the character with ASCII value v.
        g A "get" call (a way to retrieve data in storage). Pop y and x, then push ASCII value of the character at that position in the program.
        @ End program.
        (i.e. a space) No-op. Does nothing.
     */

}
