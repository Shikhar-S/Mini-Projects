from socket import *
serverIP = '127.0.0.1'
serverPort = 65530
serverSocket=socket(AF_INET,SOCK_DGRAM)
serverSocket.bind((serverIP,serverPort))
print 'Server Ready'
i=0
while(i<10):
	msg,clnt=serverSocket.recvfrom(50)
	serverSocket.sendto(msg,clnt) 
	i+=1
serverSocket.close()