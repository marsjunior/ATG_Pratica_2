# GraphLibrary
A library with graph's util functions as a part of a Graph Theory Applications course in UFCG.

Biblioteca de Grafos
Apresentação
Aplicação de Teoria dos Grafos - Turma 01 - 2018.1

Data: 15/05/2018

Grupo: 
Anderson Dalbert
Lucas Salvino
Filipe Teotônio
Matheus Andrade

Esse documento irá apresentar as principais decisões de design e implementação das funcionalidades solicitadas na biblioteca e testes, abordando cada funcionalidade individualmente.

Funções

Graph readGraph(path) / Graph readWeightedGraph(path)
Decisões de implementação: O grafo é lido linha por linha, onde cada uma destas é dividida pelos espaços que existem. A cada linha, uma nova Edge é criada e adicionada ao grafo que é nada mais que um HashMap<String, ArrayList<String>>. Onde a primeira parte é o rótulo do vértice e a outra um array para outros vértices com os quais o primeiro se conecta.
Justificativa: Como os rótulos dos nós do grafo podem ser do tipo String, optou-se por usar a estrutura citada acima com o intuito de facilitar a usabilidade e ao mesmo tempo torná-la o mais genérica possível.
Testes: Os testes certificam que os métodos leem corretamente os grafos no formato esperado. Garantem que o grafo não repetirá Edges e que não aceitará Edges como "1 1", onde um vértice vai pra si próprio.
Autor: Filipe Teotônio
  
int getVertexNumber (graph), int getEdgeNumber (graph) e float getMeanEdge (graph)
Decisões de implementação: Como a estrutura do grafo é um HashMap, esses métodos foram simples de serem implementados, pois esta estrutura fornece métodos de contagem que facilitaram muito. O getMeanEdge possui uma fórmula matemática simples para obter o resultado, fórmula esta que é baseado no valor retornado pelos dois métodos anteriores, o que simplificou bastante sua implementação.
Justificativa: Por questões de simplicidade, métodos do próprio HashMap foram utilizados para realizar as contagens.
Testes: Os testes cobrem casos onde o grafo é nulo, onde o grafo possui arestas repetidas (que são ignoradas) e demais casos triviais. 
Autor: Filipe Teotônio

String graphRepresentation (graph, type)
Decisões de implementação: Este método possui dois métodos auxiliares que são chamados de acordo com o type passado. 
Justificativa: Dessa forma, fica mais fácil de manter e de testar. Nesse método, usa-se um atributo que é informado na hora de instanciação do grafo, que é o boolean isWeighted. Assim, sabendo se o grafo contém pesos ou não, a forma como sua impressão é realizada muda, pois o peso para as arestas devem ser revelados.
Testes: Os testes abrangem todos os possíveis tipos (AM e AL) e uma combinação destes com grafos com peso e sem peso.
Autor: Filipe Teotônio

String BFS(graph, v) e DFS(graph, v)
Decisões de implementação: A busca em largura situa-se na classe GrapHandler, mas as operações são realizadas na classe Graph. Os vértices são acessados em ordem inversa ao método de busca por profundidade (head primeiro, nós folha por último). As informações vão sendo acrescentadas na lista resultante, que será também utilizada pelo método DFS.
Justificativa: Reutilizar  código da BFS na DFS facilita o entendimento e poupa esforços. A única diferença significativa ocorre nos métodos na classe GraphHandler, pois o array é impresso de maneira ordenada na BFS, enquanto que no caso da DFS apenas o primeiro elemento de cada posição da lista (começando a partir da última posição) é impresso. As operações internas são realizadas na classe Graph pois é ela que atua como Information Expert dos elementos internos do grafo, respeitando o padrão GRASP.
Testes: Os testes compreendem desde grafos unitários, simples de três vértices, até grafos desconexos e de nós com múltiplos filhos.
Autor: Matheus Andrade

String shortestPath(graph, v1, v2)
Decisões de implementação: O algoritmo de Bellman-Ford foi implementado.
Justificativa: Como o grafo pode conter arestas negativas, optou-se por usar o algoritmo de menor caminho de Bellman-Ford. Algoritmo este que além de suportar arestas negativas, ainda é capaz de detectar se há ciclos negativos.
Testes: Os testes abrangem casos onde o grafo é nulo, possui caminhos com arestas negativas e ainda grafos com ciclos negativos.
Autor: Filipe Teotônio

boolean connected(graph)
Decisões de implementação: Utilizamos como base o algoritmo de busca em profundidade para verificar se um grafo é ou não conexo, de forma ligeiramente modificada. Tomamos como princípio o fato de que, após efetuada uma busca em profundidade, se algum vértice do grafo não foi acessado, então ele é desconexo.
Primeiro, é efetuada uma busca em profundidade no grafo partindo de um vértice qualquer, e à medida os vértices que vão sendo acessados, eles são marcados. Ao final da busca, verificamos se existe algum vértice que não foi acessado, a função retorna “false” indicando que o grafo é desconexo, ou “true” indicando que o grafo é conexo. 
Justificativa: Optamos por essa abordagem para que a implementação ficasse coesa com os conceitos já abordados na biblioteca, que também implementa uma funcionalidade de busca em largura. 
Testes: Os testes cobrem e verificam grafos que são conexos e outros que são desconexos. Além disso, verificam se a funcionalidade está correta para tipos diferentes de grafo, com ou sem peso.
Autor: Anderson Dalbert

String MST(graph)
Decisões de implementação: Obter a árvore geradora mínima implicar em retornar um subGrafo com as arestas de menor de modo que se forme uma árvore. Para que o grafo seja considerado uma árvore ele não pode pode ter ciclos. Para a implementação do algoritmo foi utilizado um algoritmo guloso que percorre todas as arestas do grafo ordenadas pelo peso e seleciona as menores arestas que não formam ciclos.
Justificativa: A técnica de algoritmo utilizada para a implementação foi o algoritmo de Kruskal.  Para implementar o Kruskal faz-se necessário: ordenar as arestas pelo peso, agrupar os vértices em subgrupos; percorrer as arestas ordenadas; checar se as arestas formam ciclos; unir os subgrupos de vértices da aresta selecionada; e retornar as arestas unidas no maior subgrupo. 
	Para checar se as arestas adicionadas na árvore resultante do algoritmo foi utilizada a técnica  conhecida como Path Compression. No Path Compression vértices do mesmo subgrupo possuem o mesmo “ancestral”. Para checar se dois vértices pertencem ao mesmo grupo basta checar se eles possuem o mesmo “ancestral”. E para checar se uma aresta forma cíclo¹ ao ser adicionada na árvore basta checar se ela possui os dois vértices no mesmo subgrupo de vértices. 
¹ Os métodos “joinGroup” e “IdentifyParent” são auxiliares do método “MST” e servem para unir subgrupos de vértices e identificar o ancestral do subgrupo de vértices respectivamente.
Testes: Testes de unidade foram feitos para entradas onde o grafo de entrada não possui ciclos. E testes com grafos que possuem ciclos foram feitos diretamente no “Main”.
Autor: Lucas André Salvino
