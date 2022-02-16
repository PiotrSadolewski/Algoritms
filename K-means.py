import csv
import random

class KMeans:
    k = 0
    e = 0
    file_path = r"C:\Users\miste\OneDrive\Pulpit\Semestr 4\Zadania python\dane\values.txt"

    def __init__(self, k, e):
        self.k = k
        self.e = e
        var = self.file_path

    def work(self):
        e = self.e
        print("e = " + str(e))
        values_list = self.create_list()
        random_numbers = random.sample(range(0, len(values_list) - 1), self.k)

        distance_list = []
        sorted_vector_list = [[] for x in range(self.k)]

        for i in range(self.k):
            distance_list.append(self.get_distance_list(values_list, values_list[random_numbers[i]]))

        sorted_vector_list = self.sort_vector(distance_list, sorted_vector_list, values_list)
        avg_list = self.get_avarage(sorted_vector_list)
        new_e = self.count_e(avg_list, sorted_vector_list)

        if e != new_e:
            self.km(values_list, avg_list, e)

    def km(self, values_list, centroid_list, e):
        distance_list = []
        sorted_vector_list = [[] for x in range(self.k)]

        for i in range(self.k):
            distance_list.append(self.get_distance_list(values_list, centroid_list[i]))

        sorted_vector_list = self.sort_vector(distance_list, sorted_vector_list, values_list)
        avg_list = self.get_avarage(sorted_vector_list)
        new_e = self.count_e(avg_list, sorted_vector_list)
        print("e = " + str(new_e))
        if e != new_e:
            self.km(values_list, avg_list, new_e)


    def count_e(self, avg_list, sorted_vector_list):
        new_e = 0
        for z in range(self.k):
            for i in range(len(sorted_vector_list)):
                tmp = avg_list[i][z]
                for p in range(len(sorted_vector_list[i])):
                    new_e += pow(float(sorted_vector_list[i][p][z]) - float(tmp), 2)
        return new_e

    def get_avarage(self, svl):
        avg = 0
        avg_list = [[]for x in range(self.k)]
        for z in range(self.k):
            for i in range(len(svl)):
                for p in range(len(svl[i])):
                    avg += float(svl[i][p][z])
                avg_list[i].append(avg / (len(svl[i])))
                avg = 0
        return avg_list


    def sort_vector(self, distance_list, sorted_vector_list, values_list):
        for i in range(len(distance_list[0])):
            minValue = float(9999)
            index2 = 0
            index = 0
            for z in range(self.k):
                if minValue > float(distance_list[z][i]):
                    minValue = float(distance_list[z][i])
                    index = z
                    index2 = i
            sorted_vector_list[index].append(values_list[index2])
        return sorted_vector_list


    def get_distance_list(self, values_list, vector):
        distance_list = []
        for i in range(len(values_list)):
            distance_list.append(self.calculate_distance(values_list[i], vector))
        return distance_list


    def create_list(self):
        file = self.file_path
        values_list = []
        with open(file, "r") as csv_file:
            reader = csv.reader(csv_file, delimiter=",")
            for line in reader:
                values_list.append(line)
        return values_list

    @staticmethod
    def calculate_distance(v_list, vector):
        distance = 0
        for i in range(len(v_list)):
            distance += pow(float(v_list[i]) - float(vector[i]), 2)
        return float(distance)
