#!/bin/bash

# Directories
MAT_HOME="/home/dhrangdhariaritesh/heapdump/mat"                 # Path to Eclipse MAT
DUMPS_FOLDER="/home/dhrangdhariaritesh/heapdump/dumps"           # Folder containing heap dumps
REPORTS_FOLDER="/home/dhrangdhariaritesh/heapdump/reports"       # Folder to store reports
ARCHIVE_FOLDER="/home/dhrangdhariaritesh/heapdump/archive"       # Archive folder for processed dumps

# Ensure required directories exist
mkdir -p "$REPORTS_FOLDER"
mkdir -p "$ARCHIVE_FOLDER"

# Process each .hprof file in the dumps folder
for HEAP_DUMP in "$DUMPS_FOLDER"/*.hprof; do
    if [ -f "$HEAP_DUMP" ]; then
        BASENAME=$(basename "$HEAP_DUMP" .hprof)
        REPORT_ZIP="$DUMPS_FOLDER/${BASENAME}_Leak_Suspects.zip"
        FINAL_REPORT_ZIP="$REPORTS_FOLDER/$(date +"%Y%m%d_%H%M%S").zip"

        echo "Processing heap dump: $HEAP_DUMP"

        # Step 2: Analyse the heap dump using ParseHeapDump.sh
        echo "Analyzing $HEAP_DUMP..."
        "$MAT_HOME/ParseHeapDump.sh" "$HEAP_DUMP" org.eclipse.mat.api:suspects
        if [ $? -eq 0 ]; then
            echo "Analysis completed for $HEAP_DUMP"
        else
            echo "Analysis failed for $HEAP_DUMP. Skipping to next file."
            continue
        fi

        # Step 3: Copy the generated Leak Suspects report to the reports folder with a timestamped name
        if [ -f "$REPORT_ZIP" ]; then
            echo "Copying report $REPORT_ZIP to $FINAL_REPORT_ZIP"
            cp "$REPORT_ZIP" "$FINAL_REPORT_ZIP"
        else
            echo "Leak Suspects report not found for $HEAP_DUMP. Skipping to next file."
            continue
        fi

        # Step 4: Archive the .hprof file and clean the dumps folder
        echo "Archiving $HEAP_DUMP to $ARCHIVE_FOLDER"
        mv "$HEAP_DUMP" "$ARCHIVE_FOLDER/"
        echo "Cleaning up dumps folder."
        rm -rf "$DUMPS_FOLDER"/*
    else
        echo "No .hprof files found in $DUMPS_FOLDER."
    fi
done

echo "All heap dump processing completed."
