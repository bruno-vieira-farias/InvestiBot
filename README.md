# InvestiBot

Bot [Telegram](https://telegram.org/) que fornece informações relevantes para investidores.
- Taxa selic atual.
- Taxa selic acumulada nos últimos 30 dias.
- Rendimento poupança nos últimos 30 dias.
- Rendimento poupança nos últimos 12 meses.

### Setup
- Crie um [Telegram bot](https://core.telegram.org/bots).
- Insira a token do seu bot no arquivo `application.properties`
```text
// src/main/resources/application.properties

telegram-bot.token={seuTelegramToken}
...
```
![Tela Inicial](/images/telaInicial.png)