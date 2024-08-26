import socket

def start_client():
    client_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)  # Create a socket object

    # host = '10.70.34.14'  # Get local machine name
    host = '10.70.34.14'
    port = 9999

    # Connect to the server on the specified host and port
    client_socket.connect((host, port))
    
    # Receive data from the server (buffer size of 1024 bytes)
    message = client_socket.recv(1024)
    print(message.decode('ascii'))
    
    # Close the client socket
    client_socket.close()

if __name__ == "__main__":
    start_client()
