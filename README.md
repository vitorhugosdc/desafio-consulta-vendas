# Sistema de Vendas

Sistema desenvolvido como desafio 4/5 para obtenção de certificado do curso Java Spring Professional da [DevSuperior](https://devsuperior.com "Site da DevSuperior").

Este sistema gerencia vendas (Sale) e vendedores (Seller). Cada venda está associada a um vendedor, e um vendedor pode ter várias vendas. O sistema permite consultas de relatórios de vendas e sumário de vendas por vendedor.

## Modelo Conceitual
![Modelo Conceitual](https://github.com/vitorhugosdc/assets/blob/main/raw/desafio-consulta-vendas/modelo-conceitual.png)

## Relatório de Vendas

### Entrada
O usuário pode informar, opcionalmente, os seguintes dados:
- **Data inicial**
- **Data final**
- **Trecho do nome do vendedor**

### Saída
O sistema retorna uma listagem paginada contendo:
- **ID da venda**
- **Data da venda**
- **Quantia vendida**
- **Nome do vendedor**

### Informações Complementares
- Se a **data final** não for informada, considerar a data atual do sistema.
- Se a **data inicial** não for informada, considerar a data atual do sistema.
- Se o **nome** não for informado, considerar o texto vazio.

## Sumário de Vendas por Vendedor

### Entrada
O usuário pode informar, opcionalmente, os seguintes dados:
- **Data inicial**
- **Data final**

### Saída
O sistema informa uma listagem contendo:
- **Nome do vendedor**
- **Soma de vendas deste vendedor no período informado**

### Informações Complementares
- As mesmas do caso de uso Relatório de Vendas


