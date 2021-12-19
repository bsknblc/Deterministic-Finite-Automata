# Deterministic-Finite-Automata
Deterministic Finite Automata for CS410 Automata Theory and Formal Languages at Ozyegin University

## Input file examples
2 *(number of states)*  
2 *(number of variables)*  
1 *(number of goal states)*  
q1 q2 *(states)*  
q2 *(goal state(s))*  
a b *(variables)*  
q1 a q1 *(q1 state’inden a ile q1 stateini gidiyor)*  
q1 b q2 *(q1 state’inden b ile q2 stateini gidiyor)*  
q2 a q2 *(q2 state’inden a ile q2 stateini gidiyor)*  
q2 b q1 *(q2 state’inden b ile q1 stateini gidiyor)*  
aba *(string to be detected)*  
ababababa *(string to be detected)*  

##  Output file
q1 q2 q2 *(route taken)*  
Accepted  
q1 q2 q2 q1 q1 q2 q2 q1 q1 *(route taken)*  
Rejected  
