#!/usr/bin/python
# -*- coding: UTF-8 -*-

import sys
import math

code = """
formula = sys.argv[1]
list = sys.argv[2].split(',')
for i in list:
    exec(i)
exec(formula)
"""
#.py引用时不运行
if __name__ == '__main__':
    exec code