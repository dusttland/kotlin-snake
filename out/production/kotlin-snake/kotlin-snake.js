if (typeof kotlin === 'undefined') {
  throw new Error("Error loading module 'kotlin-snake'. Its dependency 'kotlin' was not found. Please, check whether 'kotlin' is loaded prior to 'kotlin-snake'.");
}
this['kotlin-snake'] = function (_, Kotlin) {
  'use strict';
  var println = Kotlin.kotlin.io.println_s8jyv4$;
  var Kind_CLASS = Kotlin.Kind.CLASS;
  var lastOrNull = Kotlin.kotlin.collections.lastOrNull_2p1efm$;
  var mutableListOf = Kotlin.kotlin.collections.mutableListOf_i5x0yv$;
  function main() {
    println('Hello snake.');
    var snake = new Snake();
    snake.grow();
    snake.grow();
    println(snake);
  }
  function Point(x, y) {
    this.x = x;
    this.y = y;
  }
  Point.prototype.translateBy_vux9f0$ = function (x, y) {
    return new Point(this.x + x | 0, this.y + y | 0);
  };
  Point.prototype.translateBy_p684vy$ = function (other) {
    return this.translateBy_vux9f0$(other.x, other.y);
  };
  Point.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'Point',
    interfaces: []
  };
  Point.prototype.component1 = function () {
    return this.x;
  };
  Point.prototype.component2 = function () {
    return this.y;
  };
  Point.prototype.copy_vux9f0$ = function (x, y) {
    return new Point(x === void 0 ? this.x : x, y === void 0 ? this.y : y);
  };
  Point.prototype.toString = function () {
    return 'Point(x=' + Kotlin.toString(this.x) + (', y=' + Kotlin.toString(this.y)) + ')';
  };
  Point.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.x) | 0;
    result = result * 31 + Kotlin.hashCode(this.y) | 0;
    return result;
  };
  Point.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && (Kotlin.equals(this.x, other.x) && Kotlin.equals(this.y, other.y)))));
  };
  function Snake() {
    this.pieces_0 = mutableListOf([SnakePiece_init(10, 10), SnakePiece_init(10, 11)]);
  }
  Snake.prototype.toString = function () {
    return 'Length: ' + this.length + ', Pieces: ' + this.pieces_0;
  };
  Snake.prototype.grow = function () {
    var tmp$;
    tmp$ = lastOrNull(this.pieces_0);
    if (tmp$ == null) {
      return;
    }
    var lastPiece = tmp$;
    var newLocation = lastPiece.location.translateBy_vux9f0$(0, 1);
    var newPiece = new SnakePiece(newLocation);
    this.pieces_0.add_11rb$(newPiece);
  };
  Object.defineProperty(Snake.prototype, 'length', {
    get: function () {
      return this.pieces_0.size;
    }
  });
  Snake.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'Snake',
    interfaces: []
  };
  function SnakePiece(location) {
    this.location = location;
  }
  SnakePiece.prototype.toString = function () {
    return 'SnakePiece(' + this.location.x + ', ' + this.location.y + ')';
  };
  SnakePiece.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'SnakePiece',
    interfaces: []
  };
  function SnakePiece_init(x, y, $this) {
    $this = $this || Object.create(SnakePiece.prototype);
    SnakePiece.call($this, new Point(x, y));
    return $this;
  }
  _.main = main;
  var package$snake = _.snake || (_.snake = {});
  package$snake.Point = Point;
  package$snake.Snake = Snake;
  package$snake.SnakePiece_init_vux9f0$ = SnakePiece_init;
  package$snake.SnakePiece = SnakePiece;
  main();
  Kotlin.defineModule('kotlin-snake', _);
  return _;
}(typeof this['kotlin-snake'] === 'undefined' ? {} : this['kotlin-snake'], kotlin);
