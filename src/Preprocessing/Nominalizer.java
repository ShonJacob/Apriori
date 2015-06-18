package Preprocessing;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

public class Nominalizer {
	public static void main(String[] args) throws Exception {
	    //nominalizeInitial();
	    nominalizeSecond();
	    //nominalizeFinal();
	}

	public static void nominalizeInitial() throws Exception {
	    /**
         *  2 age
         *  3 is_male
         *  8 cp
         *  9 trestbps
         *  11 chol
         *  15 fbs
         *  18 restecg
         *  31 thalach
         *  37 exang
         *  39 oldpeak
         *  40 slope
         *  43 ca
         *  50 thal
         *  57 TYPE
         */
	    
	    BufferedReader br = new BufferedReader(new FileReader("/home/vedavyas/project/data/heart/cleveland/clev"));
        FileWriter writer = new FileWriter("/home/vedavyas/project/data/heart/cleveland/initial/data/clev_0_1");

        String line = "";
        int count = 0;
        while((line = br.readLine()) != null) {
            String[] oldvalues = line.split(",");

            count++;
            //attribute age numeric
            int age = Integer.parseInt(oldvalues[2]);
            System.out.print(count+" Age: "+age);
            if(age == -9) {
                writer.append("unknown,");
            }
            else if (age >= 25 && age < 35) {
                writer.append("young,");
            }
            else if(age >= 35 && age < 50) {
                writer.append("middle,");
            }
            else if(age >= 50 && age < 65) {
                writer.append("old,");
            }
            else if (age >= 65) {
                writer.append("very_old,");
            }    


            //attribute is_male numeric
            System.out.print(", isMale: "+oldvalues[3]);
            switch (oldvalues[3]) {
                case "0": writer.append("no,"); break;
                case "1": writer.append("yes,"); break;
                default: writer.append("unknown,"); break;
            }
            

            //attribute cp numeric
            System.out.print(", cp: "+oldvalues[8]);
            switch(oldvalues[8]) {
                case "1": writer.append("typical_angina,"); break;
                case "2": writer.append("atypical_angina,"); break;
                case "3": writer.append("non_anginal_pain,"); break;
                case "4": writer.append("asymptomatic,"); break;
                default: writer.append("unknown,"); break;
            }    
            


            //attribute trestbps numeric
            int trestbps = Integer.parseInt(oldvalues[9]);
            System.out.print(", trestbps: "+trestbps);
            if (trestbps == -9) {
                writer.append("unknown,"); 
            }
            if (trestbps >= 90 && trestbps < 120) {
                writer.append("normal,");
            }
            else if(trestbps >= 120 && trestbps < 140) {
                writer.append("unusual,");
            }
            else if(trestbps >= 140 && trestbps < 160) {
                writer.append("high,");
            }
            else if (trestbps >= 160) {
                writer.append("very_high,");
            }
            


            //attribute chol numeric
            int chol = Integer.parseInt(oldvalues[11]);
            System.out.print(", chol: "+chol);
            if (chol == -9) {
                writer.append("unknown,");
            }
            if (chol >= 110 && chol < 200) {
                writer.append("normal,");
            }
            else if (chol >= 200 && chol < 240) {
                writer.append("borderline_high,");
            }
            else if (chol >= 240 && chol < 360) {
                writer.append("high,");
            }
            else if (chol >= 360) {
                writer.append("very_high,");
            }
            

            //attribute fbs numeric
            System.out.print(", fbs: "+oldvalues[15]);
            switch (oldvalues[15]) {
                case "0": writer.append("no,"); break;
                case "1": writer.append("yes,"); break;
                default: writer.append("unknown,"); break;
            }

            
            //attribute restecg numeric
            System.out.print(", restecg: "+oldvalues[18]);
            switch(oldvalues[18]) {
                case "0": writer.append("normal,"); break;
                case "1": writer.append("st_t_wave_abnormality,"); break;
                case "2": writer.append("left_ventricular_hypertrophy,"); break;
                default: writer.append("unknown,"); break;
            }
            
            
            //attribute thalach numeric
            int thalach = Integer.parseInt(oldvalues[31]);
            System.out.print(", thalach: "+thalach);
            if (thalach ==  -9) {
                writer.append("unknown,");
            }
            else if (thalach >= 70 && thalach < 100) {
                writer.append("normal,");
            }
            else if (thalach >= 100 && thalach < 130) {
                writer.append("middle,");
            }
            else if (thalach >= 130 && thalach < 160) {
                writer.append("high,");
            }
            else if (thalach >= 160) {
                writer.append("very_high,");
            }
            
            
            //attribute exang numeric
            System.out.print(", exang: "+oldvalues[37]);
            switch (oldvalues[37]) {
                case "0": writer.append("no,"); break;
                case "1": writer.append("yes,"); break;
                default: writer.append("unknown,"); break;
            }
            

            //attribute oldpeak numeric
            double oldpeak = Double.parseDouble(oldvalues[39]);
            System.out.print(", oldpeak: "+oldpeak);
            if (oldpeak ==  -9) {
                writer.append("unknown,");
            }
            else if (oldpeak >= 0 && oldpeak < 2) {
                writer.append("small,");
            }
            else if (oldpeak >= 2 && oldpeak < 4) {
                writer.append("medium,");
            }
            else if (oldpeak >= 4) {
                writer.append("large,");
            }


            //attribute slope numeric
            System.out.print(", slope: "+oldvalues[40]);
            switch(oldvalues[40]) {
                case "1": writer.append("upsloping,"); break;
                case "2": writer.append("flat,"); break;
                case "3": writer.append("downsloping,"); break;
                default: writer.append("unknown,"); break;
            }
            
            
            //attribute ca numeric
            System.out.print(", ca: "+oldvalues[43]);
            switch(oldvalues[43]) {
                case "0": writer.append("0,"); break;
                case "1": writer.append("1,"); break;
                case "2": writer.append("2,"); break;
                case "3": writer.append("3,"); break;
                default: writer.append("unknown,"); break;
            }

            //attribute thal numeric
            System.out.print(", thal: "+oldvalues[50]);
            switch(oldvalues[50]) {
                case "3": writer.append("normal,"); break;
                case "6": writer.append("fixed_defect,"); break;
                case "7": writer.append("reversible_defect,"); break;
                default: writer.append("unknown,"); break;
            }

            
            //attribute TYPE
            System.out.println(", TYPE: "+oldvalues[57]);
            switch (oldvalues[57]) {
                case "0": writer.append("0\n"); break;
                case "1": writer.append("1\n"); break;
                case "2": writer.append("1\n"); break;
                case "3": writer.append("1\n"); break;
                case "4": writer.append("1\n"); break;
                default: writer.append("unknown"); break;
            }
        }
        writer.flush();
        br.close();
        writer.close();
        System.out.println("DONE!");
	}
	
	public static void nominalizeSecond() throws Exception {
	    /**
         *  2 age
         *  3 is_male
         *  8 cp
         *  9 trestbps
         *  11 chol
         *  13 cigs
         *  14 years
         *  15 fbs
         *  16 dm
         *  17 famhis
         *  18 restecg
         *  30 met
         *  31 thalach
         *  32 thalrest
         *  33 tpeakbps
         *  34 tpeakbpd
         *  36 trestbpd
         *  37 exang
         *  39 oldpeak
         *  40 slope
         *  42 rldv5e
         *  43 ca
         *  50 thal
         *  57 TYPE
         */
	    BufferedReader br = new BufferedReader(new FileReader("/home/vedavyas/project/data/heart/hungarian/hungarian"));
        FileWriter writer = new FileWriter("/home/vedavyas/project/data/heart/hungarian/hungarian_nominalized");

        String line = "";
        int count = 0;
        while((line = br.readLine()) != null) {
            String[] oldvalues = line.split(",");

            count++;
            //attribute age numeric
            int age = Integer.parseInt(oldvalues[2]);
            System.out.print(count+" Age: "+age);
            if(age == -9) {
                writer.append("unknown,");
            }
            else if (age >= 25 && age < 35) {
                writer.append("young,");
            }
            else if(age >= 35 && age < 50) {
                writer.append("middle,");
            }
            else if(age >= 50 && age < 65) {
                writer.append("old,");
            }
            else if (age >= 65) {
                writer.append("very_old,");
            }    


            //attribute is_male numeric
            System.out.print(", isMale: "+oldvalues[3]);
            switch (oldvalues[3]) {
                case "0": writer.append("no,"); break;
                case "1": writer.append("yes,"); break;
                default: writer.append("unknown,"); break;
            }
            

            //attribute cp numeric
            System.out.print(", cp: "+oldvalues[8]);
            switch(oldvalues[8]) {
                case "1": writer.append("typical_angina,"); break;
                case "2": writer.append("atypical_angina,"); break;
                case "3": writer.append("non_anginal_pain,"); break;
                case "4": writer.append("asymptomatic,"); break;
                default: writer.append("unknown,"); break;
            }    
            


            //attribute trestbps numeric
            int trestbps = Integer.parseInt(oldvalues[9]);
            System.out.print(", trestbps: "+trestbps);
            if (trestbps == -9) {
                writer.append("unknown,"); 
            }
            if (trestbps >= 90 && trestbps < 120) {
                writer.append("normal,");
            }
            else if(trestbps >= 120 && trestbps < 140) {
                writer.append("unusual,");
            }
            else if(trestbps >= 140 && trestbps < 160) {
                writer.append("high,");
            }
            else if (trestbps >= 160) {
                writer.append("very_high,");
            }
            


            //attribute chol numeric
            int chol = Integer.parseInt(oldvalues[11]);
            System.out.print(", chol: "+chol);
            if (chol == -9) {
                writer.append("unknown,");
            }
            if (chol >= 110 && chol < 200) {
                writer.append("normal,");
            }
            else if (chol >= 200 && chol < 240) {
                writer.append("borderline_high,");
            }
            else if (chol >= 240 && chol < 360) {
                writer.append("high,");
            }
            else if (chol >= 360) {
                writer.append("very_high,");
            }
            
            //attribute cigs numeric
            int cigs = Integer.parseInt(oldvalues[13]);
            if (cigs == -9) {
                writer.append("unknown,");
            }
            else if (cigs == 0) {
                writer.append("no,");
            }
            else if (cigs > 0 && cigs <= 3) {
                writer.append("low,");
            }
            else if (cigs > 3 && cigs <= 7) {
                writer.append("medium,");
            }
            else if (cigs > 7  && cigs <= 11) {
                writer.append("high,");
            }
            else if (cigs > 11) {
                writer.append("very_high,");
            }
            
            
            //attribute years numeric
            int years = Integer.parseInt(oldvalues[14]);
            if (years == -9) {
                writer.append("unknown,");
            }
            else if (years == 0) {
                writer.append("no,");
            }
            else if (years > 0 && years <= 2) {
                writer.append("low,");
            }
            else if (years > 2 && years <= 5) {
                writer.append("medium,");
            }
            else if (years > 5 && years <= 8) {
                writer.append("high,");
            }
            else if (years > 8) {
                writer.append("very_high,");
            }
            

            //attribute fbs numeric
            System.out.print(", fbs: "+oldvalues[15]);
            switch (oldvalues[15]) {
                case "0": writer.append("no,"); break;
                case "1": writer.append("yes,"); break;
                default: writer.append("unknown,"); break;
            }

            
            //attribue dm numeric
            System.out.print(", dm: "+oldvalues[16]);
            switch (oldvalues[16]) {
                case "0": writer.append("no,"); break;
                case "1": writer.append("yes,"); break;
                default: writer.append("unknown,"); break;
            }
            
            
            //attribute famhis numeric
            System.out.print(", fmahis: "+oldvalues[17]);
            switch (oldvalues[17]) {
                case "0": writer.append("no,"); break;
                case "1": writer.append("yes,"); break;
                default: writer.append("unknown,"); break;
            }
            

            //attribute restecg numeric
            System.out.print(", restecg: "+oldvalues[18]);
            switch(oldvalues[18]) {
                case "0": writer.append("normal,"); break;
                case "1": writer.append("st_t_wave_abnormality,"); break;
                case "2": writer.append("left_ventricular_hypertrophy,"); break;
                default: writer.append("unknown,"); break;
            }

            
            //attribute met numeric
            /**
             * MET can be categorized based on the type of activity being performed:
             * light_intensive (< 3), moderate_intensive (3 to 6), vigorous (6 to 11),
             * very_vigorous (11 to 15), extremely_vigorous (15 to 19)
             * (Wikipedia)  
             * 
             */
            float met = Float.parseFloat(oldvalues[30]);
            System.out.print(", met: "+met);
            if (met == -9) {
                writer.append("unknown,");
            }
            else if (met > 0 && met <= 3) {
                writer.append("light_intensive,");
            }
            else if (met > 3 && met <= 6) {
                writer.append("moderate_intensive,");
            }
            else if (met > 6 && met <= 11) {
                writer.append("vigorous,");
            }
            else if (met > 11 && met <= 15) {
                writer.append("very_vigorous,");
            }
            else if (met > 15 && met <= 19) {
                writer.append("extremely_vigorous,");
            }


            //attribute thalach numeric
            int thalach = Integer.parseInt(oldvalues[31]);
            System.out.print(", thalach: "+thalach);
            if (thalach ==  -9) {
                writer.append("unknown,");
            }
            else if (thalach >= 70 && thalach < 100) {
                writer.append("normal,");
            }
            else if (thalach >= 100 && thalach < 130) {
                writer.append("middle,");
            }
            else if (thalach >= 130 && thalach < 160) {
                writer.append("high,");
            }
            else if (thalach >= 160) {
                writer.append("very_high,");
            }
            
            
            //@attribute thalrest numeric
            float thalrest = Float.parseFloat(oldvalues[32]);
            System.out.print(", thalrest: "+thalrest);
            if (thalrest == -9) {
                writer.append("unknown,");
            }
            else if (thalrest > 35 && thalrest <= 85) {
                writer.append("normal,");
            }
            else if (thalrest > 85) {
                writer.append("high,");
            }
            
            
            //attribute tpeakbps numeric
            float tpeakbps = Float.parseFloat(oldvalues[33]);
            System.out.print(", tpeakbps: "+tpeakbps);
            if (tpeakbps == -9) {
                writer.append("unknown,");
            }
            else if (tpeakbps > 80 && tpeakbps <= 120) {
                writer.append("normal,");
            }
            else if (tpeakbps > 120 && tpeakbps <= 180) {
                writer.append("high,"); 
            }
            else {
                writer.append("very_high,"); 
            }
            
            //attribute tpeakbpd numeric
            float tpeakbpd = Float.parseFloat(oldvalues[34]);
            System.out.print(", tpeakbpd: "+tpeakbpd);
            if (tpeakbpd ==  -9) {
                writer.append("unknown,");
            }
            else if (tpeakbpd > 24 && tpeakbpd <= 60) {
                writer.append("normal,");
            }
            else if (tpeakbpd > 60 && tpeakbpd <= 90) {
                writer.append("high,"); 
            }
            else if (tpeakbpd >  90) {
                writer.append("very_high,"); 
            }
            
            
            //attribute trestbpd numeric
            float trestbpd = Float.parseFloat(oldvalues[36]);
            System.out.print(", trestbpd: "+trestbpd);
            if (trestbpd ==  -9) {
                writer.append("unknown,");
            }
            if (trestbpd >= 50 && trestbpd < 80) {
                writer.append("normal,");
            }
            else if (trestbpd >= 80) {
                writer.append("high,");
            }
            


            //attribute exang numeric
            System.out.print(", exang: "+oldvalues[37]);
            switch (oldvalues[37]) {
                case "0": writer.append("no,"); break;
                case "1": writer.append("yes,"); break;
                default: writer.append("unknown,"); break;
            }
            

            //attribute oldpeak numeric
            double oldpeak = Double.parseDouble(oldvalues[39]);
            System.out.print(", oldpeak: "+oldpeak);
            if (oldpeak ==  -9) {
                writer.append("unknown,");
            }
            else if (oldpeak >= 0 && oldpeak < 2) {
                writer.append("small,");
            }
            else if (oldpeak >= 2 && oldpeak < 4) {
                writer.append("medium,");
            }
            else if (oldpeak >= 4) {
                writer.append("large,");
            }


            //attribute slope numeric
            System.out.print(", slope: "+oldvalues[40]);
            switch(oldvalues[40]) {
                case "1": writer.append("upsloping,"); break;
                case "2": writer.append("flat,"); break;
                case "3": writer.append("downsloping,"); break;
                default: writer.append("unknown,"); break;
            }
            
            /*
            //@attribute rldv5e numeric
            float rldv5e = Float.parseFloat(oldvalues[42]);
            System.out.print(", rldv5e: "+rldv5e);
            if (rldv5e == -9) {
                writer.append("unknown,");
            }
            else if (rldv5e > 23 && rldv5e <= 60) {
                writer.append("low,");
            }
            else if (rldv5e > 60 && rldv5e <= 130) {
                writer.append("normal,");
            }
            else if (rldv5e > 130 && rldv5e <= 190) {
                writer.append("high,");
            }
            else if (rldv5e > 190) {
                writer.append("very_high,");
            }*/


            //attribute ca numeric
            System.out.print(", ca: "+oldvalues[43]);
            switch(oldvalues[43]) {
                case "0": writer.append("0,"); break;
                case "1": writer.append("1,"); break;
                case "2": writer.append("2,"); break;
                case "3": writer.append("3,"); break;
                default: writer.append("unknown,"); break;
            }

            //attribute thal numeric
            System.out.print(", thal: "+oldvalues[50]);
            switch(oldvalues[50]) {
                case "3": writer.append("normal,"); break;
                case "6": writer.append("fixed_defect,"); break;
                case "7": writer.append("reversible_defect,"); break;
                default: writer.append("unknown,"); break;
            }

            
            //attribute TYPE
            System.out.println(", TYPE: "+oldvalues[57]);
            switch (oldvalues[57]) {
                case "0": writer.append("0\n"); break;
                case "1": writer.append("1\n"); break;
                case "2": writer.append("1\n"); break;
                case "3": writer.append("1\n"); break;
                case "4": writer.append("1\n"); break;
                default: writer.append("unknown"); break;
            }
        }
        writer.flush();
        br.close();
        writer.close();
        System.out.println("DONE!");
	}

    public static void nominalizeFinal() throws Exception {
		BufferedReader br = new BufferedReader(new FileReader("/home/vedavyas/project/data/heart/cleveland/clev"));
		FileWriter writer = new FileWriter("/home/vedavyas/project/data/heart/cleveland/final/data/clev_0_1");

		String line = "";
		int count = 0;
		while((line = br.readLine()) != null) {
			String[] oldvalues = line.split(",");

			count++;
			//attribute age numeric
		    int age = Integer.parseInt(oldvalues[2]);
            System.out.print(count+" Age: "+age);
            if(age == -9) {
                writer.append("unknown,");
            }
            else if (age >= 25 && age < 35) {
                writer.append("young,");
            }
            else if(age >= 35 && age < 50) {
                writer.append("middle,");
            }
            else if(age >= 50 && age < 65) {
                writer.append("old,");
            }
            else if (age >= 65) {
                writer.append("very_old,");
            }    


			//attribute is_male numeric
			System.out.print(", isMale: "+oldvalues[3]);
			switch (oldvalues[3]) {
				case "0": writer.append("no,"); break;
				case "1": writer.append("yes,"); break;
				default: writer.append("unknown,"); break;
			}
			

			//attribute cp numeric
		    System.out.print(", cp: "+oldvalues[8]);
            switch(oldvalues[8]) {
                case "1": writer.append("typical_angina,"); break;
                case "2": writer.append("atypical_angina,"); break;
                case "3": writer.append("non_anginal_pain,"); break;
                case "4": writer.append("asymptomatic,"); break;
                default: writer.append("unknown,"); break;
            }    
			


			//attribute trestbps numeric
		    int trestbps = Integer.parseInt(oldvalues[9]);
            System.out.print(", trestbps: "+trestbps);
            if (trestbps == -9) {
                writer.append("unknown,"); 
            }
            if (trestbps >= 90 && trestbps < 120) {
                writer.append("normal,");
            }
            else if(trestbps >= 120 && trestbps < 140) {
                writer.append("unusual,");
            }
            else if(trestbps >= 140 && trestbps < 160) {
                writer.append("high,");
            }
            else if (trestbps >= 160) {
                writer.append("very_high,");
            }
			


			//attribute chol numeric
			int chol = Integer.parseInt(oldvalues[11]);
			System.out.print(", chol: "+chol);
			if (chol == -9) {
			    writer.append("unknown,");
			}
			if (chol >= 110 && chol < 200) {
				writer.append("normal,");
			}
			else if (chol >= 200 && chol < 240) {
				writer.append("borderline_high,");
			}
			else if (chol >= 240 && chol < 360) {
				writer.append("high,");
			}
			else if (chol >= 360) {
			    writer.append("very_high,");
			}
			
			//attribute cigs numeric
			int cigs = Integer.parseInt(oldvalues[13]);
			if (cigs == -9) {
			    writer.append("unknown,");
			}
			else if (cigs == 0) {
			    writer.append("no,");
			}
			else if (cigs > 0 && cigs <= 3) {
			    writer.append("low,");
			}
			else if (cigs > 3 && cigs <= 7) {
			    writer.append("medium,");
			}
			else if (cigs > 7  && cigs <= 11) {
                writer.append("high,");
            }
			else if (cigs > 11) {
			    writer.append("very_high,");
			}
			
			
			//attribute years numeric
			int years = Integer.parseInt(oldvalues[14]);
			if (years == -9) {
			    writer.append("unknown,");
			}
			else if (years == 0) {
			    writer.append("no,");
			}
			else if (years > 0 && years <= 2) {
			    writer.append("low,");
			}
			else if (years > 2 && years <= 5) {
			    writer.append("medium,");
			}
			else if (years > 5 && years <= 8) {
			    writer.append("high,");
			}
			else if (years > 8) {
			    writer.append("very_high,");
			}
			

			//attribute fbs numeric
			System.out.print(", fbs: "+oldvalues[15]);
			switch (oldvalues[15]) {
				case "0": writer.append("no,"); break;
				case "1": writer.append("yes,"); break;
				default: writer.append("unknown,"); break;
			}

			
			//attribue dm numeric
			System.out.print(", dm: "+oldvalues[16]);
			switch (oldvalues[16]) {
			    case "0": writer.append("no,"); break;
			    case "1": writer.append("yes,"); break;
			    default: writer.append("unknown,"); break;
			}
			
			
			//attribute famhis numeric
			System.out.print(", fmahis: "+oldvalues[17]);
			switch (oldvalues[17]) {
                case "0": writer.append("no,"); break;
                case "1": writer.append("yes,"); break;
                default: writer.append("unknown,"); break;
            }
			

			//attribute restecg numeric
			System.out.print(", restecg: "+oldvalues[18]);
			switch(oldvalues[18]) {
				case "0": writer.append("normal,"); break;
				case "1": writer.append("st_t_wave_abnormality,"); break;
				case "2": writer.append("left_ventricular_hypertrophy,"); break;
				default: writer.append("unknown,"); break;
			}
			

			//attribute met numeric
			/**
			 * MET can be categorized based on the type of activity being performed:
			 * light_intensive (< 3), moderate_intensive (3 to 6), vigorous (6 to 11),
			 * very_vigorous (11 to 15), extremely_vigorous (15 to 19)
			 * (Wikipedia)  
			 * 
			 */
			float met = Float.parseFloat(oldvalues[30]);
			System.out.print(", met: "+met);
			if (met == -9) {
			    writer.append("unknown,");
			}
			else if (met > 0 && met <= 3) {
			    writer.append("light_intensive,");
			}
			else if (met > 3 && met <= 6) {
                writer.append("moderate_intensive,");
            }
			else if (met > 6 && met <= 11) {
                writer.append("vigorous,");
            }
			else if (met > 11 && met <= 15) {
                writer.append("very_vigorous,");
            }
			else if (met > 15 && met <= 19) {
                writer.append("extremely_vigorous,");
            }


			//attribute thalach numeric
			int thalach = Integer.parseInt(oldvalues[31]);
			System.out.print(", thalach: "+thalach);
			if (thalach ==  -9) {
			    writer.append("unknown,");
			}
			else if (thalach >= 70 && thalach < 100) {
				writer.append("normal,");
			}
			else if (thalach >= 100 && thalach < 130) {
				writer.append("middle,");
			}
			else if (thalach >= 130 && thalach < 160) {
				writer.append("high,");
			}
			else if (thalach >= 160) {
				writer.append("very_high,");
			}
			
			
			//@attribute thalrest numeric
			float thalrest = Float.parseFloat(oldvalues[32]);
			System.out.print(", thalrest: "+thalrest);
			if (thalrest == -9) {
			    writer.append("unknown,");
			}
			else if (thalrest > 35 && thalrest <= 85) {
			    writer.append("normal,");
			}
			else if (thalrest > 85) {
			    writer.append("high,");
			}
			
			
			//attribute tpeakbps numeric
			float tpeakbps = Float.parseFloat(oldvalues[33]);
			System.out.print(", tpeakbps: "+tpeakbps);
			if (tpeakbps == -9) {
			    writer.append("unknown,");
			}
			else if (tpeakbps > 80 && tpeakbps <= 120) {
			    writer.append("normal,");
			}
			else if (tpeakbps > 120 && tpeakbps <= 180) {
			    writer.append("high,"); 
			}
			else {
                writer.append("very_high,"); 
            }
			
			//attribute tpeakbpd numeric
            float tpeakbpd = Float.parseFloat(oldvalues[34]);
            System.out.print(", tpeakbpd: "+tpeakbpd);
            if (tpeakbpd ==  -9) {
                writer.append("unknown,");
            }
            else if (tpeakbpd > 24 && tpeakbpd <= 60) {
                writer.append("normal,");
            }
            else if (tpeakbpd > 60 && tpeakbpd <= 90) {
                writer.append("high,"); 
            }
            else if (tpeakbpd >  90) {
                writer.append("very_high,"); 
            }
			
			
			//attribute trestbpd numeric
            float trestbpd = Float.parseFloat(oldvalues[36]);
            System.out.print(", trestbpd: "+trestbpd);
            if (trestbpd ==  -9) {
                writer.append("unknown,");
            }
            if (trestbpd >= 50 && trestbpd < 80) {
                writer.append("normal,");
            }
            else if (trestbpd >= 80) {
                writer.append("high,");
            }
            


			//attribute exang numeric
            System.out.print(", exang: "+oldvalues[37]);
			switch (oldvalues[37]) {
				case "0": writer.append("no,"); break;
				case "1": writer.append("yes,"); break;
				default: writer.append("unknown,"); break;
			}
			

			//attribute oldpeak numeric
			double oldpeak = Double.parseDouble(oldvalues[39]);
			System.out.print(", oldpeak: "+oldpeak);
			if (oldpeak ==  -9) {
			    writer.append("unknown,");
			}
			else if (oldpeak >= 0 && oldpeak < 2) {
				writer.append("small,");
			}
			else if (oldpeak >= 2 && oldpeak < 4) {
				writer.append("medium,");
			}
			else if (oldpeak >= 4) {
				writer.append("large,");
			}


			//attribute slope numeric
			System.out.print(", slope: "+oldvalues[40]);
			switch(oldvalues[40]) {
				case "1": writer.append("upsloping,"); break;
				case "2": writer.append("flat,"); break;
				case "3": writer.append("downsloping,"); break;
				default: writer.append("unknown,"); break;
			}
			
			
			//@attribute rldv5e numeric
			float rldv5e = Float.parseFloat(oldvalues[42]);
			System.out.print(", rldv5e: "+rldv5e);
			if (rldv5e == -9) {
			    writer.append("unknown,");
			}
			else if (rldv5e > 23 && rldv5e <= 60) {
			    writer.append("low,");
			}
			else if (rldv5e > 60 && rldv5e <= 130) {
			    writer.append("normal,");
			}
			else if (rldv5e > 130 && rldv5e <= 190) {
                writer.append("high,");
            }
			else if (rldv5e > 190) {
			    writer.append("very_high,");
			}


			//attribute ca numeric
			System.out.print(", ca: "+oldvalues[43]);
			switch(oldvalues[43]) {
				case "0": writer.append("0,"); break;
				case "1": writer.append("1,"); break;
				case "2": writer.append("2,"); break;
				case "3": writer.append("3,"); break;
				default: writer.append("unknown,"); break;
			}

			//attribute thal numeric
			System.out.print(", thal: "+oldvalues[50]);
			switch(oldvalues[50]) {
				case "3": writer.append("normal,"); break;
				case "6": writer.append("fixed_defect,"); break;
				case "7": writer.append("reversible_defect,"); break;
				default: writer.append("unknown,"); break;
			}

		    //lmt 58
			writer.append(oldvalues[58]+",");
			
			
			//ladprox 59
			writer.append(oldvalues[59]+",");
			
			
			//laddist 60
			writer.append(oldvalues[60]+",");
			
			
			//cxmain 62
			writer.append(oldvalues[62]+",");
			
			
			//om1 64
			writer.append(oldvalues[64]+",");
			
			
			//rcaprox 66
			writer.append(oldvalues[66]+",");
			
			
			//rcadist 67
			writer.append(oldvalues[67]+",");
			
			
			System.out.println(", type: "+oldvalues[57]);
			switch (oldvalues[57]) {
				case "0": writer.append("0\n"); break;
				//attribute type {0,1,2,3,4}
				case "1": writer.append("1\n"); break;
				case "2": writer.append("1\n"); break;
				case "3": writer.append("1\n"); break;
				case "4": writer.append("1\n"); break;
				default: writer.append("unknown"); break;
			}
		}
		writer.flush();
		br.close();
	    writer.close();
	    System.out.println("DONE!");
	}
}