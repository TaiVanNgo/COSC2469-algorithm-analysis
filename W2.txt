Problem 1
The running time of your algorithm is: T(N) = 999*N - SQRT(N)

Prove that T(N) = O(N^2)

Note: SQRT is the square root function.

Problem 2
Describe an algorithm that prints out all the unique elements in an array. What is the complexity of your algorithm? (Consider two different approaches: without sorting and with sorting)

Example:

Array = [6, 8, 10, 11, 6, 10] => print out [6, 8, 10, 11] (you can print the values in any order)

Note: some students proposed using a set ADT to solve this problem. However, the operations on a set are not basic operations.

Problem 3
Given the arrival and departure time of planes reaching a particular airport. You need to find the minimum number of gates required to accommodate the planes at any point in time.

For example:

P1 = {1:00, 1:10}

P2 = {1: 40, 3:00}

P3 = {1:50, 2:20}

P4 = {2:00, 2:30}

P5 = {2:15, 3:15}

P6 = {4:00, 6:00}

=> number of gates required = 4

What is the complexity of your algorithm? In this problem, N is the number of planes.

Note: you can simplify the above problems by assuming that the arrival time and departure time are just numbers. 

Problem 4
Given an array of numbers (array size >= 2), find two in the array whose sum is closest to zero.

What is the complexity of your algorithm?

Example 1: [2, 3, 9, 6] => 2 and 3

Example 2: [-100, 50, -52, 99] => -100 and 99

Problem 5
You develop an application that can track users' movement (think of Google Maps). The application complexity is O(N^3) and it takes 100 msec to run data for 1,000 users (N is the number of users). How many days it will take your application to run for 1,000,000 users?