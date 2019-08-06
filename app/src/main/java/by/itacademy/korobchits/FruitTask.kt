package by.itacademy.korobchits

fun indexOfStartWith(list: List<String?>, value: String, startWith: Int): Int {
    for (i in startWith until list.size) {
        if (list[i] == value)
            return i
    }
    return -1
}

fun checkWin(codeList: List<List<String>>, shoppingCart: List<String?>): Int {
    var sizeShoppingCart = shoppingCart.size
    var sizeCodeList = codeList.sumBy { it.size }

    if (sizeShoppingCart < sizeCodeList) return 0

    var index: Int
    var lastIndex = -1

    for (i in 0 until codeList.size) {
        for (j in 0 until codeList[i].size) {
            val searchProduct = codeList[i][j]

            if (searchProduct == "anything") {
                lastIndex++
                continue
            }

            index = indexOfStartWith(shoppingCart, codeList[i][j], lastIndex + 1)
            if (j == 0)
                lastIndex = index - 1
            if (index == -1 || (sizeShoppingCart - index < sizeCodeList - j) || (index - 1 != lastIndex))
                return 0

            lastIndex = index
        }
        sizeCodeList -= codeList[i].size
    }
    return 1
}