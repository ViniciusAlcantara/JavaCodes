class ExemploArq
{
  public static void main(String[] args)
  {
     int inteiro = 1;
     double real = 5.3;
     char caractere = 'X';
     boolean boleano = true;
     String str = "Algoritmos";


     //ABRIR, ESCREVER E FECHAR ARQUIVO TEXTO
     Arq.openWrite("exemplo.txt");
     Arq.println(inteiro);
     Arq.println(real);
     Arq.println(caractere);
     Arq.println(boleano);
     Arq.println(str);
     Arq.close();


     //ABRIR, LER E FECHAR ARQUIVO TEXTO
     Arq.openRead("exemplo.txt");
     inteiro = Arq.readInt();
     real = Arq.readDouble();
     caractere = Arq.readChar();
     boleano = Arq.readBoolean();
     str = Arq.readString();
     Arq.close();

     System.out.println("inteiro: " + inteiro);
     System.out.println("real: " + real);
     System.out.println("caractere: " + caractere);
     System.out.println("boleano: " + boleano);
     System.out.println("str: " + str);


/*
     //ABRIR, LER (DE OUTRA FORMA) E FECHAR ARQUIVO TEXTO
     Arq.open("exemplo.txt");
     inteiro = Integer.parseInt(Arq.readLine());
     real = Double.parseDouble(Arq.readLine().replace(",","."));
     caractere = Arq.readLine().charAt(0);
     boleano = Boolean.parseBoolean(Arq.readLine());
     str = Arq.readLine();
     Arq.close();

     System.out.println("inteiro: " + inteiro);
     System.out.println("real: " + real);
     System.out.println("caractere: " + caractere);
     System.out.println("boleano: " + boleano);
     System.out.println("str: " + str);
*/
  } // Fim main()
} // Fim class
