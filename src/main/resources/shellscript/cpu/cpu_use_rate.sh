#!/bin/bash
proc_stat=$(awk 'NR==1  {sub(/[^ ]+ /, ""); print $0}' /proc/stat)
_cpuValueArray=(${proc_stat})
echo "${_cpuValueArray[0]}"