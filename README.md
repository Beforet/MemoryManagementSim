CPU Memory Management Simulator

This program simulates the processes by which a CPU loads processes into memory based
on three algorithms: Best First, Worst Fit, First Fit.

The program uses two input files placed in the root directory of the project.

Minput.data defines the memory, and what block are available for processes when the simulation
begins. The files should be in the following format:

Minput.data:
3 (number of free memory slots)
100 400 (addresses of start and end of a free memory slot => size 300)
600 800 (addresses of start and end of a free memory slot => size 200)
1500 1900 (addresses of start and end of a free memory slot => size 400)

Pinput.data defines the processes that the CPU needs to place into memory. The file should be
in the following format:

Pinput.data
3(# of processes)
1 (ID of process) 190 (size of process)
2 (ID of process) 210 (size of process)
3 (ID of process) 205 (size of process)

The output.data files are created when the program completes, and display
the location of the different processes according to the algorithm, as well
as any processes that could not be placed in memory.

output.data
100(addresses of start) 310(addresses of end) 2(process ID)
600(addresses of start) 790(addresses of end) 1(process ID)
1500(addresses of start) 1705(addresses of end) 3(process ID)
-0 (means all are allocated, or -1,3 if processes 1 and 3 can't be allocated)

This program was made as part of a school project for SUNY College at New Paltz's Operating Systems course.
This readme includes some text from the project documentation, in particular the format for the input and
output files. 