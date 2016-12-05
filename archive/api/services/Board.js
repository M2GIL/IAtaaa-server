Constantes = require('Constantes');

let initBoard = "";
if (initBoard == "") {
    for (let i = 0; i < 20; ++i) {
        initBoard += Constantes.BLACK_PAWN;
    }
    for (let i = 0; i < 10; ++i) {
        initBoard += Constantes.EMPTY;
    }
    for (let i = 0; i < 20; ++i) {
        initBoard += Constantes.WHITE_PAWN;
    }
}

regexpBoard = `^[${Constantes.EMPTY}${Constantes.WHITE_PAWN}${Constantes.WHITE_QUEEN}${Constantes.BLACK_PAWN}${Constantes.BLACK_QUEEN}]$`;
class RuleManager{
  constructor() {
    this.board = initBoard;
    this.whiteToPlay = true;
  }

  setBoard(board, whiteToPlay) {
    if (!board.match(regexpBoard)) {
      throw false;
    }
    this.whiteToPlay = whiteToPlay;
    this.board = board;
  }

  isValidMove(move, player) {
      return true;
  }
}