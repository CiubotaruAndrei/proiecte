# Proiecte

Online medication platform

Aceasta aplicatie vine in ajutorul doctorilor si a asistentilor, astfel acestia isi pot monitoriza mult mai
bine pe cine au in grija si pot elabora un plan de medicamentatie pentru un pacient. Si pacientii pot folosi
aplicatia pentru a-si vedea datele personale si retetele eliberate de doctor.
Aplicatia respecta arhitectura client-server. Clientii comunica cu serverele prin request-uri si asteapta
raspunsul de la acesta sub forma de mesaje. Serverul ruleaza unul sau mai multe programe server si partajeaza
resursele existente cu clientii. Pentru a mentine legatura intre cei doi se floloseste conceptul de sesiune, care de
obicei e limitata in timp.

Aplicatia poate fi utilizata de trei tipuri de useri:
- doctorul: are posibilitatea de a efectua operatiile de CRUD pe pacienti, asistenti si
medicamente si poate crea un plan de medicamentatie pentru un anumit pacient
- asistentul: are posibilitatea de a vedea pacientii pe care ii are in grija
- pacientul: isi poate vedea informatiile proprii si planele de medicamentatie

Un plan de medicamentatie poate contine mai multe medicamente astfel pentru un anumit pacient mai
intai se creeaza un nou plan de medicamentatie(reteta) care contine perioada in care se desfasoara tratamentul,
iar apoi pentru aceasta reteta se adauga cate un medicament impreuna cu prescriptia doctorului ce consta intr-o
descriere a intervalelor de timp in care trebuie luat acel medicament.
Medicamentul este reprezentat de nume, dozaj si efecte secundare spre exemplu paracetamol 500mg si
prescriptia acestuia.

Userul aplicatei este reprezentat de nume, email, parola, gen, data nasterii, adresa, iar pacientul are in
plus un trecut medical. In momentul in care doctorul adauga un pacient, acestuia ii este atasat doctoul logat in
acel moment in aplicatie si un asistent ales de catre doctor.

Proiectele realizate in timpul facultatii m-au ajutat sa inteleg conceptele OOP.

Cele mai importante proicete sunt:
- Bank management: Aplicatia simuleaza o banca, un client poate avea conturi de doua tipuri si poate efectua depunere si retragere
- Database management: Aplicatia permite cautarea, adaugarea, editarea si stergerea clientilor sau produselor din baza de date. Pe langa acestea se poate plasa o noua comanda, odata cu aceasta generandu-se o factura.
- Queue management: Aplicatia simuleaza un sistem de procesare al cozilor. Un exemplu din lumea reala il poate reprezenta cozile de clienti de la un supermarket. Pentru a putea implementa mai multe cozi care sa isi desfasoara activitatea in acelasi timp am folosit thread-urile.
- Stream processing: Aplicatia indeplineste anumite task-uri pe baza unui fisier de activitati. Am folosit expresii lambda si procesarea stream-urilor
