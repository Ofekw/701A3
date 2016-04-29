# 701A3

Requirements

The system has to allow for the game of Mancala (perhaps more correctly called Kalah, but it is sold under the name "Mancala"). This game is played between two people. The details of the game (adapted from the Wikipedia article on Kalah (Links to an external site.)) are as follows.

Kalah is a board game between two players. Each player has a set number of houses (typically 6) and a store. Each house begins with a set number of seeds (4 is common, but other numbers are frequently used). The houses and stores (sometimes collectively called "pits") are arranged on a rectangular board, with the stores at each end and the houses along the edge. A player controls the houses closest to her and the store on her right. The object of the game is to capture more seeds than the opponent.

A move by a player consists of the player choosing one of her houses with seeds in it, and "sowing" those seeds on the board. Sowing consists of moving around the board in an anti-clockwise direction starting with the house following the one chosen, and placing 1 seed in each house until all the seeds from the original house have been sown. A seed is also sown in the player's store, but not the opponent's store. Possible outcomes of a move are:

The last seed is sown in a house that is either not owned by the player, or the house already contains at least one seed. In which case it is the other player's turn.
The last seed is sown in the player's store. In which case the same player gets another move. There is no limit to how often this can happen.
The last seed is sown in one of the player's own houses, that house is empty, and the opposite house owned by the opponent has at least one seed in it. In which case the seed just sown and all the seeds in the opposite house are moved to the player's store. This is a "capture".
The game ends when the player whose turn it is has no possible moves (that is, all houses owned by the player are empty). The score for a player is determined by adding all the seeds in that player's houses and store.

Your implementation must assume keyboard input (stdin) and ASCII console output (stdout). 
