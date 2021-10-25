import {combineReducers} from "redux";
import mediaReducer from './media-player.reducer';

export default combineReducers({
  media: mediaReducer
});