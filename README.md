# EDAF65 - Projekt

## Beskrivning
Applikationen kommer vara ett spel. X antal klienter kommunicerar med en server via TCP för att kunna möta varandra. Spelet i fråga kommer vara Sten, Sax, Påse. Spelare kommer att kunna utmana varandra i par och se en topplista över alla aktiva spelare. När man har öppnat ett spel med en annan klient så kommer man i tur och ordning kunna välja sten, sax eller påse. Man kommer även ha möjligheten att chatta med varandra. Om en klient kopplar ifrån servern kommer den att få ett nytt id när den kopplar upp sig igen och därav inte kunna bygga på sin poäng i topplistan. Stängs servern ner kommer topplistan att rensas eftersom vi inte har någon databas att spara den i.

## Gränssnitt
Applikationen kommer ha två vyer. En vy för aktiva spelare med en topplista och en vy över ett aktivt spel. För att koppla upp sig mot en spelare så trycker man på dess namn. En koppling mellan dem skapas då. I vyn över ett aktivt spel finns ett chattfönster samt tre knappar för sten, sax och påse. Man kommer vid varje omgång bli ombedd att ange sten, sax eller påse och när båda har angivit sitt val kommer servern räkna ut om någon vann eller om det blev lika.
