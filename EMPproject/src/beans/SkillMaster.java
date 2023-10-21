package beans;

public class SkillMaster {
	
		private int skillmasterid;
		private String skillname;
		
		
		public SkillMaster() {
			// TODO Auto-generated constructor stub
		}
		
		
		
		public SkillMaster(String skillname) {
			super();
			this.skillname = skillname;
		}
		public SkillMaster(int skillmasterid, String skillname) {
			super();
			this.skillmasterid = skillmasterid;
			this.skillname = skillname;
		}
		public int getSkillmasterid() {
			return skillmasterid;
		}
		public void setSkillmasterid(int skillmasterid) {
			this.skillmasterid = skillmasterid;
		}
		public String getSkillname() {
			return skillname;
		}
		public void setSkillname(String skillname) {
			this.skillname = skillname;
		}



		
		
		
}
