import { BusStop} from '../model/busStop'

export interface Line{
    id : Number;
    name : string;
    stations: BusStop[];
}