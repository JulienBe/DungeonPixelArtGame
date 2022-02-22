package fnldg.g

object GTime {
  var d = 0f
  var d5 = 0f
  var oneMinusD5 = 0f
  var playerD = 0f
  var playerTime = 0f
  var time = 0f

  var oneToFour = 1
  var oneToHeight = 1
  var to20 = 1
  var to50 = 0
  var to150 = 0
  var alternate = true

  fun majDeltas(delta: Float, playerDelta: Float) {
    this.playerD = playerDelta
    playerTime += playerDelta
    time += delta
    d = delta
    d5 = d * 5f
  }

  fun alternate() {
    alternate = !alternate
    if (++oneToFour > 4)
      oneToFour = 1
    if (++oneToHeight > 8)
      oneToHeight = 1
    if (++to20 > 20)
      to20 = 1
    if (++to50 > 50)
      to50 = 1
    if (++to150 > 150)
      to150 = 1
  }
}