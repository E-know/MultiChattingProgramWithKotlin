import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.ServerSocket
import java.net.Socket
import java.util.logging.Logger

class MultiChatServer {
	val ss: ServerSocket = ServerSocket(8888)
	var s: Socket = ss.accept()
	val logger = Logger.getLogger(this.javaClass.name)
	val chatThreads = ArrayList<ChatThread>()
	init {

	}

	public fun start(): Unit {
		logger.info("MultiChatServer Start")

		while (true) {
			s = ss.accept();

			var chat = ChatThread()
			chatThreads.add(chat)
			chat.start()
		}
	}

	inner class ChatThread : Thread() {
		lateinit var msg: String
		lateinit var m: Message
		private val gson = Gson()

		override fun run() {
			var inMsg = BufferedReader(InputStreamReader(s.getInputStream()))
			var outMsg = PrintWriter(s.getOutputStream(), true)

			while (true) {
				msg = inMsg.readLine()
				m = gson.fromJson(msg, Message::class.java)

				if(m.type == "login"){
					//로그인
			}
				else if(m.type == "logout"){
					//로그아웃
				}
				else{
					//메세지 전송
				}
			}
			this.interrupt()
			logger.info("{${this.name} 종료됨")
		}
	}
}