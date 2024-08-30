# Initial step: Ask for user's identity
user_name = input("Please enter your name: ")
print(f"Hello, {user_name}! Let's start the simulation of vector clocks.\n")

class VectorProcess:
    def __init__(self, process_id, total_processes):
        self.process_id = process_id
        self.clock = [0] * total_processes
        self.event_log = []  # Log to keep track of events

    def increment_clock(self):
        # Increment the clock for the current process
        self.clock[self.process_id] += 1

    def internal_event(self):
        # Simulate an internal event by incrementing the clock
        self.increment_clock()
        event = f"P{self.process_id} internal event"
        self.event_log.append((event, self.clock.copy()))
        print(f"{event} at vector time {self.clock}")

    def send_message(self, receiver):
        # Simulate sending a message by incrementing the clock and sending it to the receiver
        self.increment_clock()
        event = f"P{self.process_id} sends message to P{receiver.process_id}"
        self.event_log.append((event, self.clock.copy()))
        print(f"{event} at vector time {self.clock}")
        receiver.receive_message(self.clock, self.process_id)

    def receive_message(self, sender_clock, sender_id):
        # Update the clock based on the received message
        self.increment_clock()
        self.clock = [max(self.clock[i], sender_clock[i]) for i in range(len(self.clock))]
        event = f"P{self.process_id} receives message from P{sender_id} with clock {sender_clock}"
        self.event_log.append((event, self.clock.copy()))
        print(f"{event} and updates clock to {self.clock}")

# Initialize processes with Vector Clocks
process1 = VectorProcess(0, 3)
process2 = VectorProcess(1, 3)
process3 = VectorProcess(2, 3)

# Simulate events with Vector Clocks
process1.internal_event()        # P1 performs an internal event
process2.send_message(process3)  # P2 sends a message to P3
process3.send_message(process1)  # P3 sends a message to P1
process1.receive_message(process3.clock, process3.process_id)  # P1 receives a message from P3
process1.internal_event()        # P1 performs another internal event
process3.internal_event()        # P3 performs an internal event
