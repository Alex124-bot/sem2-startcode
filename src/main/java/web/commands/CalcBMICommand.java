package web.commands;

import business.exceptions.UserException;
import business.services.BmiFacade;
import business.services.BmiUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

public class CalcBMICommand extends CommandUnprotectedPage
{
    private BmiFacade bmiFacade;

    public CalcBMICommand(String pageToShow)
    {
        super(pageToShow);
        this.bmiFacade = new BmiFacade(database);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {

        int user_id = 1; // TODO skal laves til dynamisk i forhold til login
        double height = 0.0;
        double weight = 0.0;
        double bmi = 0.0;
        String category = "";
        String gender = request.getParameter("gender");
        int sport_id = Integer.parseInt(request.getParameter("sport"));

        String[] hobbies = request.getParameterValues("hobby");
        List<String> hobbyList = null;
        if (hobbies != null)
        {
            hobbyList = Arrays.asList(hobbies);
        }

        try {
            height = Double.parseDouble(request.getParameter("height"));
            weight = Double.parseDouble(request.getParameter("weight"));

        }
        catch (NumberFormatException ex)
        {
            request.setAttribute("error", "Husk at du skal indtaste to heltal i formularen");
            return "index";
//            throw new UserException("Husk at du skal indtaste to heltal i formularen");
        }

        bmi = BmiUtil.calcBMI(height, weight);
        category = BmiUtil.getCategory(bmi);

        request.setAttribute("bmi", String.format("%.2f", bmi));
        request.setAttribute("height", height);
        request.setAttribute("weight", weight);
        request.setAttribute("category", category);
        request.setAttribute("gender", gender);
        request.setAttribute("sport_id", sport_id);
        request.setAttribute("hobbies", hobbyList);

        bmiFacade.insertBmiEntry(bmi, height, weight, category, gender, sport_id, user_id, hobbyList);

        return pageToShow;
    }
}
