MyHost
In fisierul MyHost am implementat logica de executare a unui task. In primul rand
am lucrat cu un PriorityBlockingQueue pentru a stoca task-urile in ordine de prioritate
si timpul de start. Am lucrat cu o variabila de tipul Task numita runningtask pentru
a sti ce task este running si ce task-uri sunt in asteptare. In run am implementat
toata logica , in interiorul unui while care ruleaza pana la shutdown().
In variabila 'task' retin task-ul care trebuie executat. Daca runningtask este null
inseamna ca nu exista niciun task care ruleaza si atunci setez runningtask cu task-ul
pe care il voi rula. Daca exista un task care ruleaza, verific daca 'task' are prioritate
mai mare decat task-ul care ruleaza si daca este preemptibil. Daca da atunci setez runningtask
cu task-ul 'task' si adaug in coada task-ul care ruleaza cu ajutorul metodei swap(). Dupa ,
simulez rularea task-ului cu ajutorul metodei sleep implementata in simulate(). De asemenea,
scad din timpul ramas al task-ului running curent o milisecunda. Daca timpul ramas al task-ului
running curent este 0 atunci inseamna ca s-a terminat executia si atunci setez runningtask cu null
si ii dau finish.


MyDispatcher
In fisierul MyDispatcher am implementat logica politicilor de planificare in functie de tipul acestora.
Am implementat intocmai logica din enunt, iar, in plus, am modificat metoda addTask() in synchronized void
pentru a face metoda thread-safe.