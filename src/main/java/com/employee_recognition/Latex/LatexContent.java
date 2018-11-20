package com.employee_recognition.Latex;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

@Component
public class LatexContent {
	@Autowired
	private ServletContext context;

	private String title;
	private String description;
	private String name = "";
	private String background = "";
	private String signature = "";
	private String date = "";
	private String awarder = "";

	public LatexContent() {
		this.signature = "signature";
		description = "In recognition of your dedicated service " + "to our customers and our company.";
		background = "award_background_1";
	}

	public File createLatexFile() {
		String mainDirectory = context.getRealPath("/");

		String latexFilePath = mainDirectory + "/award_files/award.tex";
		File latexFile = null;

		try {
			latexFile = new File(latexFilePath);
			BufferedWriter writer = new BufferedWriter(new FileWriter(latexFile));
			writer.write(this.getContent());
			writer.close();

		} catch (FileNotFoundException e) {
			throw new RuntimeException("There's an error in creating latex file.");
		} catch (IOException e) {
			throw new RuntimeException("There's an error in creating latex file.");
		}

		return latexFile;
	}

	public String getContent() {
		String content;
		content = "\\documentclass[paper=letter,oneside,fontsize=16pt, landscape, parskip=full]{scrartcl}\n";
		content += "\\usepackage{contour}\n";
		content += "\\usepackage[onehalfspacing]{setspace}\n";
		content += "\\usepackage[placement=top]{background}\n";
		content += "\\newcommand{\\setfont}[1]{\\fontfamily{#1}\\selectfont}\n";
		content += "\\pagestyle{empty}\n";
		content += "\\begin{document}\n";
		content += "\\backgroundsetup{scale = 1, angle = 0, opacity = 0.6,\n";
		content += "contents = {\\includegraphics[width = \\paperwidth,\n";
		content += "height = \\paperheight] {" + this.background + "}}}\n";
		content += "\\parbox[c][2cm][s]{18.5cm}{\n";
		content += "\\bfseries\\center\n";
		content += "\\Huge\\textcolor{blue}{" + this.title + "}}\n";
		content += "\\bfseries\\center\n";
		content += "\\begin{spacing}{2}\n";
		content += "\\large{Presented to}\\\n";
		content += "\\end{spacing}\n";
		content += "\\LARGE\\textit{" + this.name + "}\n\n\n";		
		content += "\\large\\parbox{18cm}{\\center{\n";
		content += this.description + "\n";
		content += "\\begin{spacing}{2.0}\\end{spacing}}}\n\n";		
		content += "\\scalebox{.7}{\n";
		content += "\\setlength{\\tabcolsep}{2.6em}\n";
		content += "\\centering{\n";
		content += "\\begin{tabular}{ccc}\n\\\\\n";
		content += "\\includegraphics[width=8cm, height=2cm]{" + this.signature + "}\n";				
		content += "& & " + this.date + "\\\\\n";
		content += "\\cline{1-1}\n";
		content += "\\cline{3-3}\n";
		content += this.awarder + " & & Date\n";
		content += "\\end{tabular}\n";
		content += "}}\n";
		content += "\\begin{spacing}{.5}\\end{spacing}\n";
		content += "\\end{document}\n";
		return content;
	}

	public void setTitle(String title) {
		this.title = title;
		if (this.title.equalsIgnoreCase("Employee of the Month")) {
			background = "award_background_1";
		} else {
			background = "award_background_2";
		}
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setBackground(String background) {
		this.background = background;
	}

	public void setSignature(String signature) {		
		if (signature == null) {
			signature = "";
		} 
		this.signature = signature.trim();		
	}

	public void setDate(String date) {
		this.date = date;
	}

	public void setAwarder(String awarder) {
		this.awarder = awarder;
	}

}