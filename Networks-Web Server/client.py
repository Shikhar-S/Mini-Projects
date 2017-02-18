from socket import *
serverIP=raw_input("Enter Server IP\n")
serverPort=input("Enter Server Port\n")
clientSocket=socket(AF_INET,SOCK_STREAM)
clientSocket.connect((serverIP,serverPort))
print 'Connection Established'
filepath=raw_input('Enter File Path\n')
command='GET '+filepath
clientSocket.send(command)
received=clientSocket.recv(1024)
if (received=='404 NOT FOUND'):
	print 'Invalid Path'
else:
	print '------The File is-----'
	print received
	print '----------------------'
clientSocket.close()