/**
 * 
 * This code is part of the OpenI40 open source advanced production scheduler
 * platform suite, have look to its licencing options.
 * Web site: http://openi40.org/  
 * Github: https://github.com/openi40/OpenI40Platform
 * We hope you enjoy implementing new amazing projects with it.
 * @author architectures@openi40.org
 *
 */
export class TimeSegment {
  utcStartDateTime?:number=0;
  utcEndDateTime?:number=0;
  effectiveAvailableTime?:number=0;
  public  isValid():boolean {
		return this.utcStartDateTime && this.utcEndDateTime  && this.utcEndDateTime>=this.utcStartDateTime && this.utcStartDateTime>0 && this.utcEndDateTime>0;
	}
  public  isLowerLimited():boolean {
		return this.utcStartDateTime && this.utcStartDateTime>0;
	}

	public   isUpperLimited():boolean {
		return this.utcEndDateTime && this.utcEndDateTime>0;
	}

	public  isLowUpLimited():boolean {
		return this.isLowerLimited() && this.isUpperLimited() && this.isValid();
	}
	public   isInRange(dateTime:number):boolean {
		let inRange:boolean  = false;
		if (this.isValid()) {
			inRange = this.utcStartDateTime<=dateTime  && this.utcEndDateTime>=dateTime;
		} else if (this.isUpperLimited()) {
			inRange = this.utcEndDateTime>=dateTime;
		} else if (this.isLowerLimited()) {
			inRange = this.utcStartDateTime>=dateTime;
		}

		return inRange;
	}

	public isSegmentInRange(range:TimeSegment ):boolean {
		let inRange:boolean  = false;
		if (range.isValid() && this.isValid()) {
			inRange = this.isInRange(range.utcStartDateTime) && this.isInRange(range.utcEndDateTime);
		} else if (range.isLowerLimited() && this.isLowerLimited() && !this.isUpperLimited()) {
			inRange = this.isInRange(range.utcStartDateTime);
		} else if (range.isUpperLimited() && this.isUpperLimited() && !this.isLowerLimited()) {
			inRange = this.isInRange(range.utcEndDateTime);
		}
		return inRange;
	}

	public   isContiguous( range:TimeSegment):boolean {
		let contiguous:boolean = range.isLowerLimited() && this.isUpperLimited() &&  this.utcEndDateTime==range.utcStartDateTime;
		contiguous = contiguous || (this.isLowerLimited() && range.isUpperLimited() && range.utcEndDateTime==this.utcStartDateTime);
		return contiguous;
	}

	public static  IsContiguous<Tr1 extends TimeSegment, Tr2 extends TimeSegment>( range:Tr1,  ranges:Array<Tr2>):boolean  {
		 let rv:boolean = false;
		ranges.forEach((r:Tr2)=>{

				rv = rv || r.isContiguous(range);

		})
		return rv;
	}

	public static   IsOverlappingVector<Tr1 extends TimeSegment, Tr2 extends TimeSegment>( range:Tr1,  ranges:Array<Tr2>):boolean {
    let rv:boolean  = false;
    ranges.forEach((r:Tr2)=>{
      rv = rv || r.IsOverlapping(range);
    })
		return rv;
	}

	public  IsOverlapping( range:TimeSegment):boolean {
		if (this.isContiguous(range)) {
			return false;
		}
		let isOverlapping:boolean  = this.isSegmentInRange(range) || range.isSegmentInRange(this);
		// Particular case of non overlapping => when ranges have Superior and Inferior
		// Bounds equals each other
		isOverlapping = isOverlapping || (range.isLowerLimited() && this.isInRange(range.utcStartDateTime));
		isOverlapping = isOverlapping || (range.isUpperLimited() && this.isInRange(range.utcEndDateTime));
		isOverlapping = isOverlapping || (this.isLowerLimited() && range.isInRange(this.utcStartDateTime));
		isOverlapping = isOverlapping || (this.isUpperLimited() && range.isInRange(this.utcEndDateTime));
		if (isOverlapping) {
			return true;
		}
		if ((this.isLowerLimited() && range.isUpperLimited() && this.utcStartDateTime >= range.utcEndDateTime || (this.isUpperLimited() && range.isLowerLimited() && this.utcEndDateTime === range.utcStartDateTime))) {
			return false;
		}

		if (!isOverlapping && this.isLowUpLimited()) {
			isOverlapping = isOverlapping || (range.isLowerLimited() && this.isInRange(range.utcStartDateTime)) || (range.isUpperLimited() && this.isInRange(range.utcEndDateTime));
		}
		if (!isOverlapping && this.isLowerLimited() && !this.isUpperLimited()) {
			isOverlapping = isOverlapping || range.isInRange(this.utcStartDateTime);

		}
		if (!isOverlapping && !this.isLowerLimited() && this.isUpperLimited()) {
			isOverlapping = isOverlapping || range.isInRange(this.utcEndDateTime);
		}
		if (!isOverlapping && !this.isUpperLimited() && !this.isLowerLimited()) {
			isOverlapping = true;
		}
		return isOverlapping;
	}

	public  equals( range:TimeSegment):boolean {
		let e:boolean = false;
		e = this.isValid() && range.isValid() && this.utcStartDateTime===range.utcStartDateTime && this.utcEndDateTime==range.utcEndDateTime;
		return e;
	}

	public static  Intersection(range:Array<TimeSegment>):TimeSegment {

		let minDateTime:number = null;
    let maxDateTime:number = null;
    if (range) {
      range.forEach((r:TimeSegment)=>{
        if (minDateTime == null) {
          minDateTime = r.utcStartDateTime;
        } else if (r.isLowerLimited()) {
          minDateTime = minDateTime>r.utcStartDateTime ? minDateTime : r.utcStartDateTime;
        }

        if (maxDateTime == null) {
          maxDateTime = r.utcEndDateTime;
        } else if (r.isUpperLimited()) {
          maxDateTime = maxDateTime>r.utcEndDateTime ? maxDateTime : r.utcEndDateTime;
        }
      })
    }


    let _tr:TimeSegment =new TimeSegment();
    _tr.utcStartDateTime=minDateTime;
    _tr.utcEndDateTime=maxDateTime;
		return _tr;
	}





	public  IsSameLimits( tr:TimeSegment):boolean {

		return (this.AreEqual(tr.utcStartDateTime, this.utcStartDateTime) && this.AreEqual(tr.utcEndDateTime, this.utcEndDateTime));
	}

	private AreEqual(startDateTime1:number, startDateTime2:number):boolean  {
		if (startDateTime1 == null && startDateTime2 == null) {
			return true;
		}
		return startDateTime1 != null && startDateTime2 != null ? startDateTime1===startDateTime2 : false;
	}

	public calculateTimeElapsedInMinutes():number {
		let duration:number = -1;
		if (this.isValid()) {
			duration = (this.utcEndDateTime-this.utcStartDateTime)/(1000.0*60.0);

		}
		return duration;
	}
}
