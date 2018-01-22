
# neuralnetworksimulation

A simple neural network simulation.
Updated 11 August 2016

Authors:
Aidan Nyquist
Kai Pinckard
Jason Kiff

Used lwjgl to implement graphics library from scratch because aidan's computer can't handle modern graphics libraries

Simulation uses two actors with initially random movement and shooting patterns. Movement and collision data is sent to 
two separate neural networks that will 'learn' from the data and output movement and shooting instructions to the simulation.

Optimally, the actors will learn to shoot more accurately and dodge effectively.
