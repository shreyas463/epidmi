# epidmi
Agent-Based Modeling (ABM):​

Utilizing Agent-Based Modeling (ABM) allows us to represent individuals as agents, each with specific characteristics and behaviors.​ ABM is particularly effective in capturing the heterogeneity and interactions among individuals in a population.​

Repast Simphony:​

Repast Simphony serves as a powerful platform for implementing ABM and conducting simulations.​
It provides a flexible and customizable environment for modeling complex systems, making it well-suited for studying disease spread dynamics.​


Motivation:

The SI (Susceptible-Infected) and SIR (Susceptible-Infected-Recovered) epidemiological models are essential for understanding infectious disease spread. The SI model, which focuses on susceptible and infected individuals, is useful for diseases that do not confer immunity post-infection. In contrast, the SIR model includes a recovered category, suitable for diseases were recovery grants immunity. Simulating these models helps predict outbreak trajectories, evaluate intervention impacts, and guide public health decisions. This is vital for efficient healthcare resource planning and reducing the societal impact of diseases.​


Methodology:

->We have used Repast Simphony to model disease spread scenarios with n number of individuals. ​

->There are 2 parts of this project. ​

->For Part A, we used the traditional fully mixed SI and SIR models​.

->For Part B, The spread is limited to individuals in contact on the network. Small World and Barabasi-Albert models are used to generate the synthetic network. Similar to Part A, the objective is to analyze changes in the number of susceptible, infected, and recovered individuals over time with different β and γ values, using the synthetic networks​

<img width="1184" alt="Screenshot 2024-01-23 at 4 53 04 PM" src="https://github.com/shreyas463/epidmi/assets/76155738/b4d3acd9-e4b6-4699-ada2-fd30c950d3f4">

Outputs:

1- SI MODEL

<img width="1186" alt="Screenshot 2024-01-23 at 4 56 20 PM" src="https://github.com/shreyas463/epidmi/assets/76155738/61737f8e-c9c4-4930-abd9-c98c24d7d5e2">

2- SIR MODEL

<img width="1184" alt="Screenshot 2024-01-23 at 4 56 57 PM" src="https://github.com/shreyas463/epidmi/assets/76155738/1d1e67b4-d424-4ec4-98aa-ff558d228550">

3- BARBASI – ALBERT / SI MODEL​

<img width="1187" alt="Screenshot 2024-01-23 at 4 58 19 PM" src="https://github.com/shreyas463/epidmi/assets/76155738/3a56ce1e-2222-4ee4-8eb8-d1ea7e9e9f4a">

4- WATTS STROGATZ - SIR MODEL​

<img width="1186" alt="Screenshot 2024-01-23 at 4 59 26 PM" src="https://github.com/shreyas463/epidmi/assets/76155738/da244efd-3523-44ff-866c-da462ff57e5d">

