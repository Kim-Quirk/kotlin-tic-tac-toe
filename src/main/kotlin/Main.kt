fun main() {
    println("Welcome to Tic-Tac-Toe! You're X's and I'm O's!")

    var answers = arrayOf(" ", " ", " ", " ", " ", " ", " ", " ", " ")
    createBoard(answers)

    var end = false
//    var count = 0
    while (!end) {
        val input = getInput()
        answers = updateAnswers(answers, input)
        createBoard(answers)
        end = testWin(answers)
        if (!end) {
            println("My turn!")
            answers = enemyMoves(answers)
            createBoard(answers)
            end = testWin(answers)
        }
    }
    playAgain()
}

fun playAgain() {
    var done = false
    var again = "EEE"
    while (!done) {
        println("Wanna play again? Enter a \"Y\" for yes or \"N\" for no.")
        again = readLine()!!
        if (again == "Y" || again == "N") {
            done = true
        } else {
            println("Could not read input. Please enter a \"Y\" for yes or \"N\" for no.")
        }
    }
    if (again == "Y") {
        main()
    }
}

fun testWin(answers: Array<String>): Boolean {
    var win = false
    if (
        (answers[0] == "X" && answers[1] == "X" && answers[2] == "X") ||
        (answers[3] == "X" && answers[4] == "X" && answers[5] == "X") ||
        (answers[6] == "X" && answers[7] == "X" && answers[8] == "X") ||
        (answers[0] == "X" && answers[3] == "X" && answers[6] == "X") ||
        (answers[1] == "X" && answers[4] == "X" && answers[7] == "X") ||
        (answers[2] == "X" && answers[5] == "X" && answers[8] == "X") ||
        (answers[0] == "X" && answers[4] == "X" && answers[8] == "X") ||
        (answers[2] == "X" && answers[4] == "X" && answers[6] == "X")
    ) {
        win = true
        println("You win! Congrats!")
    }
    if (
        (answers[0] == "O" && answers[1] == "O" && answers[2] == "O") ||
        (answers[3] == "O" && answers[4] == "O" && answers[5] == "O") ||
        (answers[6] == "O" && answers[7] == "O" && answers[8] == "O") ||
        (answers[0] == "O" && answers[3] == "O" && answers[6] == "O") ||
        (answers[1] == "O" && answers[4] == "O" && answers[7] == "O") ||
        (answers[2] == "O" && answers[5] == "O" && answers[8] == "O") ||
        (answers[0] == "O" && answers[4] == "O" && answers[8] == "O") ||
        (answers[2] == "O" && answers[4] == "O" && answers[6] == "O")
    ) {
        win = true
        println("I win! Better luck next time.")
    }
    var count = 0
    for (i in 0..8) {
        if (answers[i] != " ") {
            count += 1
        }
    }
    if (count == 9) {
        win = true
        println("It's a tie. No one wins!")
    }
    return win
}

fun createBoard(answers: Array<String>) {
    println("   A   B   C ")
    println("1  "+ answers[0] +" | "+ answers[1] +" | "+ answers[2] +" ")
    println("  ---+---+---")
    println("2  "+ answers[3] +" | "+ answers[4] +" | "+ answers[5] +" ")
    println("  ---+---+---")
    println("3  "+ answers[6] +" | "+ answers[7] +" | "+ answers[8] +" ")
}

fun updateAnswers(answers: Array<String>, input: String): Array<String> {
    var x = (input[0].uppercaseChar().code - 65)
    if (input[1].toString() == "2") {
        x += 3
    }
    if (input[1].toString() == "3") {
        x += 6
    }
    if (answers[x] != " ") {
        println("Error! The spot you indicated has already been used. Try again")
        return answers
    }
    answers[x] = "X"
    return answers
}

fun enemyMoves(answers: Array<String>): Array<String> {
    var error = true
    var x = 0
    while (error) {
        x = (0..8).random()
        if (answers[x] == " ") {
            error = false
        }
    }
    answers[x] = "O"
    return answers
}

fun getInput(): String {
    print("Where would you like to place your \"X\"? Please enter a letter followed by a number. ")
    var stringInput = readLine()!!
    while (stringInput.length > 2 || stringInput.length <= 1) {
        println("Invalid input. Please try again.")
        print("Where would you like to place your \"X\"? Please enter a letter followed by a number. ")
        stringInput = readLine()!!
    }
    val char2 = stringInput[0]
    val char1 = stringInput[1]
    var check = false
    while (!check) {
        if (char1.toString() == "1" || char1.toString() == "2" || char1.toString() == "3") {
            if (char2.uppercaseChar().toString() == "A"
                || char2.uppercaseChar().toString() == "B"
                || char2.uppercaseChar().toString() == "C")
            {
                println("Correct format!")
                check = true
            }
        }
        if (!check) {
            println("Invalid input. Please try again.")
            print("Where would you like to place your \"X\"? Please enter a letter followed by a number. ")
            stringInput = readLine()!!
        }
    }
    return stringInput
}