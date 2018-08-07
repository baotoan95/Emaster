export class Statement {
    id: string;
	type: string;
	content: string;
	correctAnswers: Statement[];
	incorrectAnswers: Statement[];
	explaination: string;
	sound: string;
	slowSound: string;
	image: string;
	language: string;
	bestAnswer: string;
	createdDate: number;
	createdBy: string;
	categoryId: string;
	updatedDate: number;
}