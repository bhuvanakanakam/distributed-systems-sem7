class Process:
    def __init__(self, process_id):
        self.process_id = process_id
        self.clock = 0

    def increment_clock(self):
        self.clock += 1

    def internal_event(self):
        self.increment_clock()
        print(f"Process {self.process_id} performs an internal event and updates clock to {self.clock}")

    def send_message(self, receiver):
        self.increment_clock()
        print(f"Process {self.process_id} sends a message to Process {receiver.process_id} with clock {self.clock}")
        receiver.receive_message(self.clock)

    def receive_message(self, sender_clock):
        self.clock = max(self.clock, sender_clock) + 1
        print(f"Process {self.process_id} receives a message with sender clock {sender_clock} and updates clock to {self.clock}")

# Initialize processes
process1 = Process(1)
process2 = Process(2)
process3 = Process(3)

# Simulate events
process1.internal_event()       # P1 performs an internal event
process2.send_message(process3) # P2 sends message to P3
process3.send_message(process1) # P3 sends message to P1
process1.receive_message(process3.clock)  # P1 receives message from P3
process1.internal_event()       # P1 performs another internal event
process3.internal_event()       # P3 performs an internal event
