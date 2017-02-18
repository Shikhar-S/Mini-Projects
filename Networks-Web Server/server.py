from socket import *
def process(command):
	if(command[0:3]==('GET')):
		path=command[4:];
		try:
			print 'trying to open with path '+path
			f=open(path)
			f_text=''
			for line in f:
				f_text+=line
			return f_text
		except:
			return '404 NOT FOUND'
	else:
		return '404 NOT FOUND'


serverIP = '127.0.0.1'
serverPort = 65530
serverSocket = socket(AF_INET,SOCK_STREAM)
serverSocket.bind((serverIP,serverPort))
serverSocket.listen(1)
print 'The server is ready to serve'
while 1:
	connSocket,addr=serverSocket.accept()
	command=connSocket.recv(1024)
	reply=process(command)
	connSocket.send(reply)
	connSocket.close()