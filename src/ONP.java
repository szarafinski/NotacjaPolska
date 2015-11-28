// wykorzystujemy klasy Scanner, Stack oraz StringTokenizer z pakietu java.util
import java.util.*;  
/**
 * Odwrotna Notacja Polska
 * Zamiana wyrażeń infiksowych na postfiksowe
 * @author kodatnik.blogspot.com
 */
public class ONP {
 // pola klasy
 private String wyrazenieInfiksowe;
 private String wyrazeniePostfiksowe; 
 
 // konstruktor dokonuje przypisania polom wyrażeń
 public ONP(String wyrazenie) {
  wyrazenieInfiksowe = wyrazenie;
  wyrazeniePostfiksowe = "";
  // wywołujemy metodę dokonującą konwersji
  infiksNaPostfiks();
 }

 // metoda konwertuje wyrażenie w postaci infiksowej na postfiksową
 // wynik operacji jest zapisywany w polu wyrazeniePostfiksowe
 private void infiksNaPostfiks() {
        
  // tworzymy pusty stos
  Stack<String> stos = new Stack<>();
        
  // dzielimy wyrażenie infiksowe na części na podstawie separatorów
  StringTokenizer st = new StringTokenizer(wyrazenieInfiksowe, "+-*/()", true);
        
  // dopóki są elementy w wyrażeniu wejściowym
  while(st.hasMoreTokens()) {

   // pobieramy element
   String s = st.nextToken();
         
   // jeżeli element jest operatorem
   if( s.equals("+") || s.equals("*") || s.equals("-") || s.equals("/")) {
    // sprawdzemy priorytety
    while(!stos.empty() && priorytet(stos.peek()) >= priorytet(s)) 
     wyrazeniePostfiksowe += stos.pop()  + " ";
     // odkładamy operator na stos
    stos.push(s);
   }
   // jeżeli element jest nawiasem otwierającym
   else if(s.equals("(")) stos.push(s);
   // jeżeli element jest nawiasem zamykającym
   else if(s.equals(")")) {
    // ściągamy operatory ze stosu, aż do nawiasu otwierajęcego
    while(!stos.peek().equals("(")) wyrazeniePostfiksowe += stos.pop() + " ";
    // ściągamy nawias otwierający
    stos.pop();
   }
   // jeżeli element nie jest operatorem ani nawiasem dodajemy go do wyrażenia postfiksowego
   else wyrazeniePostfiksowe += s  + " ";

  }
  // ściągamy ze stosu pozostałe operatory i dodajemy je do wyrażenia postfiksowego
  while(!stos.empty()) wyrazeniePostfiksowe += stos.pop()  + " ";
 } 
  
 // metoda zwraca priorytet operatora
 public static int priorytet(String operator) {
  // dla + i - zwracamy 1
  if(operator.equals("+") || operator.equals("-")) return 1;
  // dla * i / zwracamy 2
  else if(operator.equals("*") || operator.equals("/")) return 2;
  // dla pozostałych 0
  else return 0;
 }
 
 // metoda zwraca nam łańcuch tekstowy z wyrażeniem w formie postfiksowej
 @Override
 public String toString() {
  return wyrazeniePostfiksowe;
 }

 public static void main(String args[]) {

  Scanner sc = new Scanner(System.in);
  System.out.print ("Podaj wyrażenie infiksowe: " );
 
  // pobieramy od użytkownika wyrażenie
  String wyrazenie = sc.nextLine();

  // tworzymy nowy obiekt klasy OdwrotnaNotacjaPolska 
  // i przekazujemy do konstruktora pobrane od użytkownika wyrażenie
  ONP onp = new ONP(wyrazenie);

  // wyświetlamy wyrażenie w postaci postfiksowej
  System.out.println ("Wyrażenie postfiksowe: " + onp);
 }
}