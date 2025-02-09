# 🚀 Docker Telegram Bot for Saving Messages to Obsidian Notes 🚀

This Dockerized Telegram bot allows you to save forwarded messages directly to your Obsidian vault as notes. Perfect for organizing your thoughts, ideas, and media in Obsidian!

---

## 🧐 How It Works 🧐

1. **Set up your Obsidian vault** on a host of your choice.
2. **Get a Telegram bot token** from [BotFather](https://core.telegram.org/bots#botfather).
3. **Deploy the Docker container** with `obsidian-tg-bot`.
4. **Configure directories** and settings.
5. **Forward messages** to the bot.
6. **PROFIT!** Your messages are saved as notes in your Obsidian vault.

---

## ✨ Features ✨

- Save messages to an `inbox.md` file.
- Preserve Markdown formatting from messages*.
- Save media files (images, videos, etc.) and embed them in the note.
- **TODO**: Customizable note templates.
- **TODO**: Save messages to different files.
- **TODO**: Full Markdown syntax support.
- **TODO**: Combine multiple media files into a single note.

---

## 🛠️ Setup 🛠️

### Docker Compose Configuration

The project includes a `docker-compose.yml` file for easy deployment:

```yaml
version: "3.4"
services:
  runner:
    image: m4gw4y/obsidian-sync-tg-bot
    container_name: obsidian-tg-bot
    environment:
      - UID=1026
      - GID=100
    restart: unless-stopped
    volumes:
      - "data:/data"
      - "./config.json:/telegram_bot/config.json:ro"
```

`data`: The directory where your Obsidian vault is stored.

`config.json`: Configuration file for the bot.

---

Configuration File
Create a config.json file with the following structure:
```json
{
  "token": "YOUR_BOT_TOKEN",
  "username": "YOUR_TELEGRAM_USERNAME"
}
```
Example:
```json
{
  "token": "ADLdsfgFRfghJDFV_+geR_GDLFKG_SDGKJ",
  "username": "markquarasique"
}
```

---

## 🤝 Contributing 🤝
Contributions are welcome! If you'd like to improve this project, feel free to open an issue or submit a pull request.

--- 

## 📜 License 📜
This project is licensed under the MIT License. See the LICENSE file for details.
