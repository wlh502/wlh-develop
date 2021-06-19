#!/bin/bash
# 当前脚本只获取一次CPU统计信息，需要外部程序不断调用
proc_stat=$(awk 'NR==1  {sub(/[^ ]+ /, ""); print $0}' /proc/stat)
_cpuValueArray=(${proc_stat})

# 空闲时间
_idleTime=${_cpuValueArray[3]}
# 总CPU时间
_totalCpuTime=0
# 遍历循环CPU不同场景下累加的节拍数-即 cpu 总时间数
# shellcheck disable=SC2068
for element in ${_cpuValueArray[@]};
do
  # 这里调用Linux bc计算器来计算
  # shellcheck disable=SC2016
  _totalCpuTime=$(echo "$_totalCpuTime+$element"| bc)
done
# 输出结果 空闲时间_总cpu时间
echo "${_idleTime}_${_totalCpuTime}"

