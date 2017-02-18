from socket import *
import time
serverIP=raw_input('Server IP\n')
serverSocket=input('Server Socket\n')
clientSocket=socket(AF_INET,SOCK_DGRAM)
clientSocket.settimeout(1)
for i in range(1,11):
	try:
		start=time.time()
		clientSocket.sendto("Hi",(serverIP,serverSocket))
		msg,addr=clientSocket.recvfrom(50)
		end=time.time()
		print 'Time Elapsed: ', (end-start)
	except:
		print 'Packet Lost'
clientSocket.close()
#Strangely loopback has higher latency.