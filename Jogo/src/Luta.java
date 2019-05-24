public class Luta{
	public Lutador rodada(Lutador a, Lutador b, char[] jogadas1, char[] jogadas2)
	{
		double lj1,lj2;
		Lutador winner = null;
		for(int i=0;i<jogadas1.length;i++)
		{
			double defesa = 0;
			double dano = 0;
			double danop = 0;
			if(a.getVida()>0)
			{
			
					//inicio da jogada do jogador 1
					if(jogadas1[i]=='1')
					{
						try{
							dano = a.atacarSocar();
							if(jogadas2[i]=='4')
							{
								defesa = b.getDefender();
								dano = dano - defesa;
							}
							System.out.println(a.getNome() + " deu um soco!");
						}catch (Exception e)
						{
							System.out.println("Erro na aplicação:" + e);
						}
					}else if(jogadas1[i]=='2')
					{
						try{
							dano = a.atacarChutar();
							if(jogadas2[i]=='4')
							{
								defesa = b.getDefender();
								dano = dano - defesa;
							}
							System.out.println(a.getNome() + " deu um chute!");
						}catch (Exception e)
						{
							System.out.println("Erro na aplicação:" + e);
						}
					}else if(jogadas1[i]=='3')
					{
						if(jogadas2[i]=='1')
						{
							danop = b.getVSoco(); 
						}else if(jogadas2[i]=='2')
						{
							danop = b.getVChute(); 
						}else if(jogadas2[i]=='3')
						{
							danop = 0;
						}else if(jogadas2[i]=='4')
						{
							danop = b.getPoder();
						}else
						{
							continue;
						}
						dano = a.defender(danop);
						if(dano==0)
						{
							System.out.println(a.getNome() + " defendeu o golpe!");
						}else{
							System.out.println(a.getNome() + " tentou defender, mas nao conseguiu!");
						} 
					}else if(jogadas1[i]=='4')
					{
						try{
							dano = a.liberarPoder();
							if(jogadas2[i]=='4')
							{
								defesa = a.getDefender();
								dano = dano - defesa;
							}
							System.out.println(a.getNome() + " soltou um especial!");
						}catch(Exception e)
						{
							System.out.println("Erro na aplicação:" + e);
						}
					}
					
					if(dano>0)
					{
						lj2 = b.getVida() - dano;
						b.setVida(lj2);
						System.out.println(a.getNome() + " causou " + dano + " de dano ao jogador " + b.getNome());
						if(b.getVida()>0)
						{
							System.out.println(b.getNome() + " agora tem " + b.getVida() + " pontos de vida restando!");
						}else
						{
							System.out.println("K.O. " + a.getNome() + " wins!");
						}
					}
					
				}else{
					i=jogadas1.length-1;
					winner = b;
				}//fim da jogada do jogador 1
				
				if(b.getVida()>0)//inicio da jogada do jogador 2
				{
					
					if(jogadas2[i]=='1')
					{
						try{
							dano = b.atacarSocar();
							if(jogadas1[i]=='4')
							{
								defesa = a.getDefender();
								dano = dano - defesa;
							}
							System.out.println(b.getNome() + " deu um soco!");
						}catch (Exception e)
						{
							System.out.println("Erro na aplicação:" + e);
						}
					}else if(jogadas2[i]==2)
					{
						try{
							dano = b.atacarChutar();
							if(jogadas1[i]=='4')
							{
								defesa = a.getDefender();
								dano = dano - defesa;
							}
							System.out.println(b.getNome() + " deu um chute!");
						}catch (Exception e)
						{
							System.out.println("Erro na aplicação:" + e);
						}
					}else if(jogadas2[i]=='3')
					{
						if(jogadas1[i]=='1')
						{
							danop = a.getVSoco(); 
						}else if(jogadas1[i]=='2')
						{
							danop = a.getVChute(); 
						}else if(jogadas1[i]=='3')
						{
							danop = 0;
						}else if(jogadas1[i]=='4')
						{
							danop = a.getPoder();
						}else
						{
							continue;
						}
						dano = b.defender(danop);
						if(dano==0)
						{
							System.out.println(b.getNome() + " defendeu o golpe!");
						}else{
							System.out.println(b.getNome() + " tentou defender, mas nao conseguiu!");
						}
					}else if(jogadas2[i]=='4')
					{
						try{
							dano = b.liberarPoder();
							if(jogadas1[i]=='4')
							{
								defesa = a.getDefender();
								dano = dano - defesa;
							}
							System.out.println(b.getNome() + " soltou um especial!");
						}catch(Exception e)
						{
							System.out.println("Erro na aplicação:" + e);
						}
					}
					if(dano>0)
					{
						lj1 = a.getVida() - dano;
						a.setVida(lj1);
						System.out.println(b.getNome() + " causou " + dano + " de dano ao jogador " + a.getNome());
						if(a.getVida()>0)
						{
							System.out.println(a.getNome() + " agora tem " + a.getVida() + " pontos de vida restando!");
						}else
						{
							System.out.println("K.O. " + b.getNome() + " wins!");
						}
						
					}
					//fim da jogada do jogador 2
				}else
				{
					i=jogadas1.length -1;
					winner = a;
				}
				if(a.getVida()<=0)
				{
					i=jogadas1.length-1;
					winner = b;
				}
		}
		return winner;
		
	}
	
}