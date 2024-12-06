# Heap Dump Processing Automation

This repository automates the process of generating, analyzing, and managing Java heap dumps using Eclipse MAT (Memory Analyzer Tool). The automation script identifies heap dumps, analyzes them, and organizes the reports.

## Setup and Workflow

### 1. Generate Heap Dump in Your Application
Run your Java application with the following options to enable automatic heap dump generation on `OutOfMemoryError`:

```bash
java -Xmx256m -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/home/dhrangdhariaritesh/heapdump/dumps -jar buggyApp.jar PROBLEM_OOM
## This will generate .hprof files in the specified path (/home/dhrangdhariaritesh/heapdump/dumps) whenever the application runs out of memory.

2. Analyze Heap Dumps
Extract and Analyze Using ParseHeapDump.sh
Eclipse MAT's ParseHeapDump.sh script is used to analyze the heap dumps and generate Leak Suspects reports.

3. Automation Script
Script: process_all_heapdumps.sh
The script performs the following tasks:

Search for .hprof Files: Iterates through all .hprof files in the dumps folder.
Analyze Each File: Runs ParseHeapDump.sh for each .hprof file to generate a Leak Suspects report.
Copy Reports: Copies the generated Leak_Suspects.zip report to the reports folder, renaming it with a timestamp.
Archive and Clean: Moves the .hprof file to the archive folder and cleans up the dumps folder.
4. Setup Instructions
Step 1: Update Paths
Edit the script to match your environment:

MAT_HOME: Path to Eclipse MAT.
DUMPS_FOLDER: Folder containing heap dumps.
REPORTS_FOLDER: Folder to store reports.
ARCHIVE_FOLDER: Folder for processed dumps.
Step 2: Make the Script Executable
chmod +x process_all_heapdumps.sh

Step 3: Run the Script
Manually execute the script to process existing heap dumps:
./process_all_heapdumps.sh

5. Automate with Cron Job
To automate the script execution every 30 minutes, follow these steps:
Step 1: Edit Cron Jobs
Open the crontab editor:
crontab -e
Step 2: Add the Cron Job
Add the following line to schedule the script:
*/30 * * * * /home/dhrangdhariaritesh/heapdump/process_all_heapdumps.sh >> /home/dhrangdhariaritesh/heapdump/cron_log.txt 2>&1
Explanation:

*/30: Runs the job every 30 minutes.
/home/dhrangdhariaritesh/heapdump/process_all_heapdumps.sh: Path to your script.
>> /home/dhrangdhariaritesh/heapdump/cron_log.txt 2>&1: Logs output and errors to cron_log.txt.
