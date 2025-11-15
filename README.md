# Analise de Código / Teste de Caixa Branca

<img width="550" height="400" alt="image" src="https://github.com/user-attachments/assets/39777ccd-390d-4f87-b7d0-8636a18d7b37" />

## Analisando o Código

### Analise o código acima e responda as seguintes perguntas:

O código foi devidamente documentado?

As variáveis e constantes possuem nomenclatura?

Existem legibilidade e organização no código?

Todos os NullPointers foram tratados?

As conexões utilizadas foram fechadas?

Link para o Excel: https://1drv.ms/x/c/967bf7c8f82502b9/ETB_kbYapxlBhWf-9PJQ8VoBnh5demZogAopRb3EU-0pWw?e=lS5aoP

# Grafo de Fluxo

<img width="800" height="300" alt="image" src="https://github.com/user-attachments/assets/c260a314-c05f-4c48-95d7-f83348c6633d" />

Link para o Grafo de Fluxo: https://www.figma.com/board/coUGxldGt5DP7vh4gFHlG4/Grafo-de-Fluxo?node-id=0-1&t=mirLFiClN9cvSKzV-1 

## Mapeando os Nós

Nó 1: String sql = "";

Nó 2: Connection conn = conectarBD();

Nó 3: sql += ... (as 3 linhas de concatenação)

Nó 4: try { (Início do bloco try)

Nó 5: Statement st = conn.createStatement();

Nó 6: ResultSet rs = st.executeQuery(sql);

Nó 7: if(rs.next()) { (Ponto de Decisão 2)

Nó 8: result = true;

Nó 9: nome = rs.getString("nome"); }

Nó 10: } catch (Exception e) { (Caminho alternativo da Decisão 1)

Nó 11: } (Ponto de junção final do try-catch)

Nó 12: return result;

## Montando o Fluxo

Aresta de 1 para 2: Início do fluxo

Aresta de 2 para 3: Continuação da sequência

Aresta de 3 para 4: Sequência vai para o início do try

Aresta de 1 para 2: Início do fluxo

Aresta de 2 para 3: Continuação da sequência

Aresta de 3 para 4: Sequência vai para o início do try

Aresta de 7 para 8: Caminho "Verdadeiro" (se rs.next() for true)

Aresta de 7 para 11: Caminho "Falso" (se rs.next() for false, ele pula os Nós 8 e 9 e vai direto para o Nó 11, que é o fim do try-catch)

Aresta de 8 para 9: Sequência dentro do if

Aresta de 9 para 11: Após o Nó 9, o fluxo se junta ao caminho "Falso", indo para o Nó 11 (o fim do try-catch)

Arestas de 5, 6, 7, 8, ou 9 para 10: Se qualquer um desses nós falhar (ex: NullPointerException no Nó 5), o fluxo é desviado para o Nó 10

Aresta de 10 para 11: Após o catch (Nó 10) executar, ele segue para o mesmo ponto de junção final (Nó 11)

Aresta de 11 para 12: O fluxo segue do ponto de junção para o return

# Cálculo de Complexidade Ciclomática

### Nós (N) = 12

A quantidade de Nós (N1, N2, N3...)

### Total de Arestas (E) = 17

Basta contar a quantidade de setas (setas = arestas)

### Total de Componentes (P) = 1

Número de "Programas" sendo analisados

## Cálculo

Substituindo os valores na fórmula: 

M = E - N + 2P

M = 17 - 12 + (2 × 1) 

M = 5 + 2 

M = 7

A complexidade ciclomática M desse código é 7

# Caminhos Básicos

### Caminho 1 (Baseline - Sucesso Total)

Este é o caminho onde o try funciona e o if é verdadeiro

Rota: 1 → 2 → 3 → 4 → 5 → 6 → 7 → 8 → 9 → 11 → 12

### Caminho 2 (Login Inválido)

Este caminho testa o if sendo falso

Rota: 1 → 2 → 3 → 4 → 5 → 6 → 7 → 11 → 12

### Caminho 3 (Exceção no Nó 9)

Este caminho testa a falha na linha nome = rs.getString("nome");

Rota: 1 → 2 → 3 → 4 → 5 → 6 → 7 → 8 → 9 → 10 → 11 → 12

### Caminho 4 (Exceção no Nó 8)

Este caminho testa a falha na linha result = true;

Rota: 1 → 2 → 3 → 4 → 5 → 6 → 7 → 8 → 10 → 11 → 12

### Caminho 5 (Exceção no Nó 7)

Este caminho testa a falha na linha if(rs.next())

Rota: 1 → 2 → 3 → 4 → 5 → 6 → 7 → 10 → 11 → 12

### Caminho 6 (Exceção no Nó 6)

Este caminho testa a falha na linha ResultSet rs = st.executeQuery(sql);

Rota: 1 → 2 → 3 → 4 → 5 → 6 → 10 → 11 → 12

### Caminho 7 (Exceção no Nó 5)

Este caminho testa a falha na linha Statement st = conn.createStatement();

Rota: 1 → 2 → 3 → 4 → 5 → 10 → 11 → 12
