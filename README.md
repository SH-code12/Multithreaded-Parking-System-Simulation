# Multithreaded-Parking-System-Simulation with Semaphores and Threads

This project simulates a parking system using semaphores and threads. The parking lot has limited spots and three gates through which cars can enter. Each car arrives at a specified time, stays for a predetermined duration, and exits when done. The system is designed to manage concurrent car arrivals and departures, ensuring smooth access to parking spots using thread synchronization.

## Table of Contents

1. [Objectives](#objectives)
2. [System Specifications](#system-specifications)
3. [Implementation](#implementation)
4. [Input Format](#input-format)
5. [Running the Simulation](#running-the-simulation)
6. [Example Output](#example-output)

---

## Objectives

- **Thread Synchronization**: Manage parking spot access using threading and semaphores.
- **Concurrency Management**: Ensure correct handling of concurrent arrivals and departures.
- **Simulation Realism**: Reflect realistic timing for car arrivals and parking durations.
- **Status Reporting**: Log each car’s activity and report the number of cars currently parked and the total served.

## System Specifications

- **Parking Spots**: 4 spots in total.
- **Gates**: 3 gates (Gate 1, Gate 2, Gate 3).
- **Car Arrival Times**: Each gate receives cars at different times based on the input schedule file.

## Implementation

1. **Setup Parking Lot**: Initialize a parking lot with 4 spots, managed by a semaphore.
2. **Gate Simulation**: Each gate receives cars at different times and is represented by a separate thread.
3. **Car Threads**: Each car is represented by a thread that attempts to park, stays for a specified duration, and exits.
4. **Logging and Reporting**: Logs each car’s activity and provides a summary report after the simulation.

### Implementation Details

- **Thread Function**: Each car attempts to acquire a parking spot via a semaphore, simulates the parking duration, and then releases the spot.
- **Timing Simulation**: Arrival and parking duration are simulated using `sleep()`.
- **Concurrency Control**: A semaphore controls parking spot availability, ensuring there are no race conditions.
- **Input Parsing**: The car arrival schedule and parking duration are read from a `.txt` file.


