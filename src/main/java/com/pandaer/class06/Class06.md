# 总结
完成了一道Hard难度的题,利用归并排序的改法解决的,想尽办法将问题引到求某个数的后面或者前面的满足条件的个数
快速排序的递归和非递归实现.
理解了为什么递归排序最差是O(N^2)以及为什么最好是O(NLogN)
最差的情况就是 有左区没有右区,这样需要比较的次数就是 N N-1 N-2 N-3 ....
最好的情况就是 左区和右区只有一半,那么递归的深度就变成了LogN 每一层的工作量是O(N) 所以总共就是N*LogN
