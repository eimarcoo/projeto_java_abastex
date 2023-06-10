import java.util.ArrayList;
import java.util.Scanner;

public class Program_PV{
    
        //Fornecedor

		//Armazena os cnpjs cadastrados
	    public static ArrayList<String> cnpjs = new ArrayList<>();

		//Armazena as razoes sociais cadastradas
        public static ArrayList<String> nomes = new ArrayList<>();

		//Armazena os ids dos fornecedores cadastrados
        public static ArrayList<Integer> id_empresas = new ArrayList<>();
		
        
        //Produtos

		//Armazena os eans cadastrados
        public static ArrayList<String> eans = new ArrayList<>();

		//Armazena as descricoes dos produtos cadastrados
        public static ArrayList<String> descricoes = new ArrayList<>();

		//Armazena os ids dos produtos cadastrados
        public static ArrayList<Integer> id_produtos = new ArrayList<>();

		//Armazena as quanrtidades dos produtos cadastrados
        public static ArrayList<Integer> quantidades = new ArrayList<>();
    
		//FORNECEDOR

		public static int create (String cnpj, String nome){

			// checagem
			int id = id_empresas.size();
			int fornecedor_existe = read(cnpj);
	
			if (fornecedor_existe >= 0){
				return -2;
			}
	
			// adiciona no banco
			int novo = id+1;
			id_empresas.add(novo);
			cnpjs.add(cnpj);
			nomes.add(nome);
	
			return 1;
	
		}
		public static int read (String cnpj){

			int indice;
			String cnpj_busca;
	
			//verifica se fornecedor existe
			for (indice = 0; indice < cnpjs.size(); indice++){
				cnpj_busca = cnpjs.get(indice);
	
				if (cnpj_busca.equals(cnpj)){
					break;
				}
	
			}
	
			if (indice == cnpjs.size()){
				return -1;
			}
	
			return indice;
	
		}

		public static int readp (String cnpj){

			int indice;
			String cnpj_busca;
	
			//verifica se fornecedor existe e busca o ID para vincular ao produto que irá ser cadastrado
			for (indice = 0; indice < cnpjs.size(); indice++){
				cnpj_busca = cnpjs.get(indice);
	
				if (cnpj_busca.equals(cnpj)){
					break;
				}
	
			}
	
			if (indice == cnpjs.size()){
				return -1;
			}
			
			indice = id_empresas.get(indice);
			return indice;
	
		}

		public static int update (String cnpj, String nome){

			//verifica se fornecedor existe
			int indice = read(cnpj);
	
			if (indice < 0){
				return -1;
			}
			
			//atualiza razao social do fornecedor
			nomes.set(indice, nome);
	
			return 1;
		}

		public static int delete (String cnpj){


			//verifica se fornecedor existe
			int indice = read(cnpj);
	
			if (indice < 0){
				return -1;
			}
	
			//exclui do banco de dados
			cnpjs.remove(indice);
			nomes.remove(indice);
	
			return 1;        
		}
	

		//PRODUTOS

		public static int createprodutos (String ean, String descricao, String cnpj){

			// checagem
			int id = eans.size();
			int produto_existe = readprodutos(ean);
	
			if (produto_existe >= 0){
				return -2;
			}
	
			// adiciona no banco
			id++;
			id_produtos.add(id);
			eans.add(ean);
			descricoes.add(descricao);
			quantidades.add(0);
	
			return 1;
	
		}
		public static int readprodutos (String ean){

			//verifica se produto existe 
			int indice;
			String ean_busca;

			for (indice = 0; indice < eans.size(); indice++){
				ean_busca = eans.get(indice);
	
				if (ean_busca.equals(ean)){
					break;
				}
	
			}
	
			if (indice == eans.size()){
				return -1;
			}
	
			return indice;
	
		} 
		
		public static int updatep (String ean, String descricao){

			//verifica se produto existe
			int indicep = readprodutos(ean);
	
			if (indicep < 0){
				return -1;
			}

			//atualiza os dados do produto
			descricoes.set(indicep, descricao);
	
			return 1;
		}
		
		public static int deletep (String ean){

			//verifica se produto existe
			int indice = readprodutos(ean);
	
			if (indice < 0){
				return -1;
			}
	
			//remove o produto do banco de dados
			eans.remove(indice);
			descricoes.remove(indice);
			id_produtos.remove(indice);
	
			return 1;        
		} 

		public static int updatecomp (String ean, int add){

			//verifica se produto existe
			int indice = readprodutos(ean);
	
			if (indice < 0){
				return -1;
			}
	
			//busca a quantidade de estoque do produto cadastrado
			int quant = quantidades.get(indice);
			
			//adicona a quantidade atual + a compra
			quantidades.set(indice, (quant+add));
	
			return 1;
		}

		public static int updatevenda (String ean, int add){

			//verifica se produto existe
			int indice = readprodutos(ean);
	
			if (indice < 0){
				return -1;
			}

			//busca a quantidade de estoque do produto cadastardo
			int quant = quantidades.get(indice);

			//verifica se a quantidade em estoque é suficiente para venda
			int veri = quant-add;

			if(veri <= 0){
				return -2;
			}
			
			quantidades.set(indice, (quant-add));
	
			return 1;
		}

	public static void main (String[] args) {
	    
	    String cnpj, nome, ean, descricao;
	    int status, menu, opf, opp, indice;
		boolean cond = true;
	    
	    Scanner scan = new Scanner(System.in);
	    
	    while (cond == true){
	    
	    System.out.println(" _______________________________________ \n|          Bem vindo a ABASTEX          |\n|Selecione uma opção para continuar     |\n|1 - Fornecedor                         |\n|2 - PRODUTOS                           |\n|3 - SAIR                               |\n|_______________________________________|");
	    System.out.print("Digite a opção: ");
	    menu = scan.nextInt();
	    
	    if (menu == 1){
	        System.out.println(" _______________________________________ \n|              FORNECEDOR               |\n|Selecione uma opção para continuar     |\n|1 - Novo Fornecedor                    |\n|2 - Listar Fornecedores                |\n|3 - Editar Fornecedor                  |\n|4 - Excluir Fornecedor                 |\n|_______________________________________|");
	        System.out.print("Digite a opção: ");
	        opf = scan.nextInt();
			if(opf == 1){
				System.out.print("Digite o CNPJ do fornecedor: ");
				cnpj = scan.next();
				System.out.print("Digite a razão social do fornecedor: ");
				nome = scan.next();

				status = create(cnpj, nome);

				if(status == 1){
					System.out.println("Fornecedor cadastrado com sucesso");
				}else {
					System.out.println("Erro ao cadastrar o fornecedor. \nCNPJ já existe.");
				}
			}else if (opf == 2){

				for (indice = 0; indice < cnpjs.size(); indice++){

                    System.out.println("ID: "+id_empresas.get(indice));
					System.out.println("CNPJ: "+cnpjs.get(indice));
                    System.out.println("Razão social: "+nomes.get(indice));
                    System.out.println("------------------------");
				}
			} else if(opf == 3){
				System.out.print("Digite o CNPJ do fornecedor: ");
                cnpj = scan.next();

                System.out.print("Digite o novo nome do Fornecedor: ");
                nome = scan.next();

                status = update(cnpj, nome);

                if (status == 1){
                    System.out.println("Fornecedor atualizado com sucesso");
                } else {
                    System.out.println("Erro ao atualizar fornecedor");
                }

			} else if(opf == 4){
				System.out.print("Digite o CNPJ do fornecedor: ");
                cnpj = scan.next();
				
				status = delete(cnpj);

                if (status == 1){
                    System.out.println("Fornecedor removido com sucesso");
                } else {
                    System.out.println("Erro ao remover fornecedor");
                }
			}

	    }else if (menu == 2){
	        System.out.println(" _______________________________________ \n|               PRODUTOS                |\n|Selecione uma opção para continuar     |\n|1 - Novo produto                       |\n|2 - Listar Produtos                    |\n|3 - Editar Produto                     |\n|4 - Excluir Produto                    |\n|5 - Compra                             |\n|6 - Venda                              |\n|_______________________________________|");
	        System.out.print("Digite a opção: ");
	        opp = scan.nextInt();

			if(opp == 1){
				System.out.print("Digite o EAN do produto: ");
				ean = scan.next();
				System.out.print("Digite a descrição: ");
				descricao = scan.next();
				System.out.print("Digite o CNPJ do fornecedor: ");
				cnpj = scan.next();


				status = createprodutos(ean, descricao, cnpj);

				if(status == 1){
					System.out.println("Produto cadastrado com sucesso");
				}else if(status == -3){
					System.out.println("Erro ao cadastrar o produto. \nCNPJ não existe.");
				}else {
					System.out.println("Erro ao cadastrar o produto. \nProduto já existe.");
				}
			}else if (opp == 2){

				for (indice = 0; indice < eans.size(); indice++){

                    System.out.println("ID: "+id_produtos.get(indice));
					System.out.println("EAN: "+eans.get(indice));
                    System.out.println("Descrição: "+descricoes.get(indice));
					System.out.println("Quantidade: "+quantidades.get(indice));
					System.out.println("Fornecedor: "+nomes.get(indice));
                    System.out.println("------------------------");
				}
			}  else if(opp == 3){
				System.out.print("Digite o EAN do produto: ");
                ean = scan.next();

                System.out.print("Digite o novo nome do produto: ");
                descricao = scan.next();

                status = updatep(ean, descricao);

                if (status == 1){
                    System.out.println("Produto atualizado com sucesso");
                } else {
                    System.out.println("Erro ao atualizar produto");
                }
			} else if(opp == 4){
				System.out.print("Digite o EAN do produto: ");
                ean = scan.next();
				
				status = deletep(ean);

                if (status == 1){
                    System.out.println("Produto removido com sucesso");
                } else {
                    System.out.println("Erro ao remover produto");
                }
			} else if(opp == 5){

				System.out.print("Digite o ean do produto: ");
                ean = scan.next();

				System.out.print("Digite a quantidade a adicionar do produto: ");
                int add = scan.nextInt();

				status = updatecomp(ean, add);

                if (status == 1){
                    System.out.println("Produto atualizado com sucesso");
                } else {
                    System.out.println("Erro ao atualizar produto");
                }
			}

			else if(opp == 6){

				System.out.print("Digite o ean do produto: ");
                ean = scan.next();

				System.out.print("Digite a quantidade a remover do produto: ");
                int add = scan.nextInt();

				status = updatevenda(ean, add);

                if (status == 1){
                    System.out.println("Produto atualizado com sucesso");
                } else if(status == -2){
					System.out.println("Produto com quantidade insuficiente");
			 	} else {
                    System.out.println("Erro ao atualizar produto");
                }
			}

	    }else if(menu == 3){
	        cond = false;
	        System.out.println("Programa encerrado");
	    }else{
	        System.out.println("A opção "+menu+" é inválida. \nPor favor, escolha uma das opções listadas.");
	    }

		}
	    
	    
}
	    
}