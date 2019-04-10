if (typeof kotlin === 'undefined') {
  throw new Error("Error loading module 'kotlin-snake'. Its dependency 'kotlin' was not found. Please, check whether 'kotlin' is loaded prior to 'kotlin-snake'.");
}
this['kotlin-snake'] = function (_, Kotlin) {
  'use strict';
  var println = Kotlin.kotlin.io.println_s8jyv4$;
  var equals = Kotlin.equals;
  var IllegalArgumentException_init = Kotlin.kotlin.IllegalArgumentException_init_pdl1vj$;
  var Kind_OBJECT = Kotlin.Kind.OBJECT;
  var Enum = Kotlin.kotlin.Enum;
  var Kind_CLASS = Kotlin.Kind.CLASS;
  var throwISE = Kotlin.throwISE;
  var lastOrNull = Kotlin.kotlin.collections.lastOrNull_2p1efm$;
  var mutableListOf = Kotlin.kotlin.collections.mutableListOf_i5x0yv$;
  Direction.prototype = Object.create(Enum.prototype);
  Direction.prototype.constructor = Direction;
  function main() {
    println('Hello snake.');
    var snake = new Snake();
    snake.grow();
    snake.grow();
    println(snake.movingDirection);
    snake.move_7z3ky7$(Direction$RIGHT_getInstance());
    snake.move_7z3ky7$(Direction$RIGHT_getInstance());
    snake.move_7z3ky7$(Direction$UP_getInstance());
    println(snake);
  }
  function Direction(name, ordinal) {
    Enum.call(this);
    this.name$ = name;
    this.ordinal$ = ordinal;
  }
  function Direction_initFields() {
    Direction_initFields = function () {
    };
    Direction$UP_instance = new Direction('UP', 0);
    Direction$RIGHT_instance = new Direction('RIGHT', 1);
    Direction$DOWN_instance = new Direction('DOWN', 2);
    Direction$LEFT_instance = new Direction('LEFT', 3);
    Direction$Companion_getInstance();
  }
  var Direction$UP_instance;
  function Direction$UP_getInstance() {
    Direction_initFields();
    return Direction$UP_instance;
  }
  var Direction$RIGHT_instance;
  function Direction$RIGHT_getInstance() {
    Direction_initFields();
    return Direction$RIGHT_instance;
  }
  var Direction$DOWN_instance;
  function Direction$DOWN_getInstance() {
    Direction_initFields();
    return Direction$DOWN_instance;
  }
  var Direction$LEFT_instance;
  function Direction$LEFT_getInstance() {
    Direction_initFields();
    return Direction$LEFT_instance;
  }
  Object.defineProperty(Direction.prototype, 'opposite', {
    get: function () {
      switch (this.name) {
        case 'UP':
          return Direction$DOWN_getInstance();
        case 'RIGHT':
          return Direction$LEFT_getInstance();
        case 'DOWN':
          return Direction$UP_getInstance();
        case 'LEFT':
          return Direction$RIGHT_getInstance();
        default:return Kotlin.noWhenBranchMatched();
      }
    }
  });
  Object.defineProperty(Direction.prototype, 'translation', {
    get: function () {
      switch (this.name) {
        case 'UP':
          return new Point(0, -1);
        case 'RIGHT':
          return new Point(1, 0);
        case 'DOWN':
          return new Point(0, 1);
        case 'LEFT':
          return new Point(-1, 0);
        default:return Kotlin.noWhenBranchMatched();
      }
    }
  });
  function Direction$Companion() {
    Direction$Companion_instance = this;
  }
  Direction$Companion.prototype.fromTranslation_p684vy$ = function (translation) {
    if (equals(translation, Direction$UP_getInstance().translation))
      return Direction$UP_getInstance();
    else if (equals(translation, Direction$RIGHT_getInstance().translation))
      return Direction$RIGHT_getInstance();
    else if (equals(translation, Direction$DOWN_getInstance().translation))
      return Direction$DOWN_getInstance();
    else if (equals(translation, Direction$LEFT_getInstance().translation))
      return Direction$LEFT_getInstance();
    else
      throw IllegalArgumentException_init("Can't get direction (translation: " + translation + ').');
  };
  Direction$Companion.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'Companion',
    interfaces: []
  };
  var Direction$Companion_instance = null;
  function Direction$Companion_getInstance() {
    Direction_initFields();
    if (Direction$Companion_instance === null) {
      new Direction$Companion();
    }
    return Direction$Companion_instance;
  }
  Direction.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'Direction',
    interfaces: [Enum]
  };
  function Direction$values() {
    return [Direction$UP_getInstance(), Direction$RIGHT_getInstance(), Direction$DOWN_getInstance(), Direction$LEFT_getInstance()];
  }
  Direction.values = Direction$values;
  function Direction$valueOf(name) {
    switch (name) {
      case 'UP':
        return Direction$UP_getInstance();
      case 'RIGHT':
        return Direction$RIGHT_getInstance();
      case 'DOWN':
        return Direction$DOWN_getInstance();
      case 'LEFT':
        return Direction$LEFT_getInstance();
      default:throwISE('No enum constant snake.Direction.' + name);
    }
  }
  Direction.valueOf_61zpoe$ = Direction$valueOf;
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
  Object.defineProperty(Point.prototype, 'negative', {
    get: function () {
      return new Point(-this.x | 0, -this.y | 0);
    }
  });
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
  Object.defineProperty(Snake.prototype, 'length', {
    get: function () {
      return this.pieces_0.size;
    }
  });
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
  Object.defineProperty(Snake.prototype, 'movingDirection', {
    get: function () {
      var moveTranslation = this.head_0.location.translateBy_p684vy$(this.neck_0.location.negative);
      return Direction$Companion_getInstance().fromTranslation_p684vy$(moveTranslation);
    }
  });
  Snake.prototype.move_7z3ky7$ = function (direction) {
    if (direction === this.movingDirection.opposite) {
      throw IllegalArgumentException_init("Can't move to snake's opposite direction.");
    }
    var nextLocation = {v: this.head_0.location.translateBy_p684vy$(direction.translation)};
    var tmp$;
    tmp$ = this.pieces_0.iterator();
    while (tmp$.hasNext()) {
      var element = tmp$.next();
      var oldLocation = element.location;
      element.location = nextLocation.v;
      nextLocation.v = oldLocation;
    }
  };
  Object.defineProperty(Snake.prototype, 'head_0', {
    get: function () {
      return this.pieces_0.get_za3lpa$(0);
    }
  });
  Object.defineProperty(Snake.prototype, 'neck_0', {
    get: function () {
      return this.pieces_0.get_za3lpa$(1);
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
  Object.defineProperty(Direction, 'UP', {
    get: Direction$UP_getInstance
  });
  Object.defineProperty(Direction, 'RIGHT', {
    get: Direction$RIGHT_getInstance
  });
  Object.defineProperty(Direction, 'DOWN', {
    get: Direction$DOWN_getInstance
  });
  Object.defineProperty(Direction, 'LEFT', {
    get: Direction$LEFT_getInstance
  });
  Object.defineProperty(Direction, 'Companion', {
    get: Direction$Companion_getInstance
  });
  var package$snake = _.snake || (_.snake = {});
  package$snake.Direction = Direction;
  package$snake.Point = Point;
  package$snake.Snake = Snake;
  package$snake.SnakePiece_init_vux9f0$ = SnakePiece_init;
  package$snake.SnakePiece = SnakePiece;
  main();
  Kotlin.defineModule('kotlin-snake', _);
  return _;
}(typeof this['kotlin-snake'] === 'undefined' ? {} : this['kotlin-snake'], kotlin);
