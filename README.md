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

## Grafo de Fluxo

<img width="800" height="300" alt="image" src="https://github.com/user-attachments/assets/c260a314-c05f-4c48-95d7-f83348c6633d" />

# Mapeando os Nós

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

Nó 12: return result; (Nó de saída)

# Montando o Fluxo

Aresta de 1 para 2: Início do fluxo.

Aresta de 2 para 3: Continuação da sequência.

Aresta de 3 para 4: Sequência vai para o início do try.

Aresta de 1 para 2: Início do fluxo.

Aresta de 2 para 3: Continuação da sequência.

Aresta de 3 para 4: Sequência vai para o início do try.

Aresta de 7 para 8: Caminho "Verdadeiro" (se rs.next() for true).

Aresta de 7 para 11: Caminho "Falso" (se rs.next() for false, ele pula os Nós 8 e 9 e vai direto para o Nó 11, que é o fim do try-catch).

Aresta de 8 para 9: Sequência dentro do if.

Aresta de 9 para 11: Após o Nó 9, o fluxo se junta ao caminho "Falso", indo para o Nó 11 (o fim do try-catch). Isso é idêntico ao N3 e N4 se juntando no N5 do seu professor.

Arestas de 5, 6, 7, 8, ou 9 para 10: Se qualquer um desses nós falhar (ex: NullPointerException no Nó 5), o fluxo é desviado para o Nó 10.

Aresta de 10 para 11: Após o catch (Nó 10) executar, ele segue para o mesmo ponto de junção final (Nó 11).

Aresta de 11 para 12: O fluxo segue do ponto de junção para o return.
