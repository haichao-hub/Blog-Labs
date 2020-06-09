
# lab-001

## Spring RestTemplate使用泛型
Spring RestTemplate使用泛型，将Class作为泛型类型转换为ParameterizedTypeReference，以简化开发代码。
但每次请求都new一个ParameterizedTypeReference对象，这点比较恶心。理想的效果是只传入一个Class对象就能确定返回值的类型，
所以我们需要做的就是想办法把class对象转换为ParameterizedTypeReference对象。
[https://blog.csdn.net/u011936655/article/details/88416978](https://blog.csdn.net/u011936655/article/details/88416978)
