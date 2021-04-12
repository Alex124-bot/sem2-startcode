package web.commands;

import business.exceptions.UserException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CalcBMICommand extends CommandUnprotectedPage
{
    public CalcBMICommand(String pageToShow) {
        super(pageToShow);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {

        Double height = 0.0;
        Double weight = 0.0;
        Double bmi = 0.0;
        String category = "";

        try {
            height = Double.parseDouble(request.getParameter("height"));
            weight = Double.parseDouble(request.getParameter("weight"));

        }
        catch (NumberFormatException ex)
        {
            throw new UserException("Husk at du skal indtaste to heltal i formularen");
        }

        bmi = weight / ((height / 100) * (height / 100));

        if(bmi > 30) {
            category = "Svært overvægtig";
        } else if (bmi < 18.50) {
            category = "Undervægtig";
        } else if (bmi < 25.00) {
            category = "Normalvægtig";
        } else {
            category = "Overvægtig";
        }

        request.setAttribute("bmi", bmi);
        request.setAttribute("height", height);
        request.setAttribute("weight", weight);
        request.setAttribute("category", category);

        return pageToShow;
    }
}
